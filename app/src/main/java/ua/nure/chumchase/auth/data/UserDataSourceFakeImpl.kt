package ua.nure.chumchase.auth.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import ua.nure.chumchase.auth.domain.OperationStatusMessage
import ua.nure.chumchase.auth.domain.UserDataSource
import ua.nure.chumchase.auth.domain.model.User
import ua.nure.chumchase.core.base.BaseResult

class UserDataSourceFakeImpl(private val dataStore: DataStore<Preferences>) : UserDataSource {

    private val gsonConverter = Gson()
    private lateinit var users: List<User>

    init {
        CoroutineScope(Dispatchers.IO).launch {
            users = getUsers()
        }
    }

    override suspend fun login(login: String, password: String): BaseResult<Boolean> {
        delay(2000)
        val result = users.find { it.login == login && it.password == password }
        return if (result != null) BaseResult(isSuccess = true)
        else BaseResult(isSuccess = false, error = OperationStatusMessage.FAILURE)
    }

    override suspend fun register(user: User): BaseResult<Boolean> {
        delay(1000)
        val result = users.toMutableList()
        result.add(user)
        dataStore.edit {
            it[USER_LIST] = gsonConverter.toJson(result)
        }
        return login(user.login, user.password)
    }

    private suspend fun getUsers(): List<User> {
        return withContext(Dispatchers.IO) {
            val jsonResult = dataStore.data.map { it[USER_LIST] ?: "" }.first()
            return@withContext if (jsonResult.isNotEmpty()) gsonConverter.fromJson(jsonResult)
            else listOf()
        }
    }

    companion object {
        private val USER_LIST = stringPreferencesKey("USER_LIST")
    }
}

internal inline fun <reified T> Gson.fromJson(json: String) =
    fromJson<T>(json, object : TypeToken<T>() {}.type)