package ua.nure.chumchase.core.data.token

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import kotlinx.coroutines.flow.first
import ua.nure.chumchase.core.base.BaseDataResult

class TokenManagerImpl(private val dataStore: DataStore<Preferences>) : TokenManager {
    override suspend fun saveToken(token: String) {
        dataStore.edit {
            it[LOGGED_USER_TOKEN] = "Bearer $token"
        }
    }

    override suspend fun getToken(): String? = dataStore.data.first()[LOGGED_USER_TOKEN]
    override suspend fun isUserLogged(): BaseDataResult<Boolean> {
        val token = getToken()
        return if (token != null) BaseDataResult(isSuccess = true, data = true)
        else BaseDataResult(isSuccess = true, data = false)
    }

    companion object {
        private val LOGGED_USER_TOKEN = stringPreferencesKey("LOGGED_USER_TOKEN")
    }
}