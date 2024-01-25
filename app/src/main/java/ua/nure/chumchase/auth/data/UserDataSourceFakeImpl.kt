package ua.nure.chumchase.auth.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import ua.nure.chumchase.auth.data.entity.Comment
import ua.nure.chumchase.auth.data.entity.User
import ua.nure.chumchase.auth.domain.OperationStatusMessage
import ua.nure.chumchase.auth.domain.UserDataSource
import ua.nure.chumchase.auth.domain.model.LoginUserDTO
import ua.nure.chumchase.auth.domain.model.RegisterUserDTO
import ua.nure.chumchase.core.base.BaseResult
import ua.nure.chumchase.feature.profile.domain.model.CommentDTO
import ua.nure.chumchase.feature.profile.domain.model.UserInfoDTO

class UserDataSourceFakeImpl(private val dataStore: DataStore<Preferences>) : UserDataSource {

    private val gsonConverter = Gson()
    private lateinit var users: List<User>

    init {
        CoroutineScope(Dispatchers.IO).launch {
            updateUsers()
        }
    }

    override suspend fun login(loginUserDTO: LoginUserDTO): BaseResult<Boolean> {
        val result =
            users.find { it.login == loginUserDTO.login && it.password == loginUserDTO.password }
        return if (result != null) {
            saveToken(result)
            BaseResult(isSuccess = true)
        } else BaseResult(
            isSuccess = false,
            error = OperationStatusMessage.FAILURE
        )
    }

    override suspend fun register(registerUserDTO: RegisterUserDTO): BaseResult<Boolean> {
        val result = users.toMutableList().plus(
            User(
                getNextId(),
                registerUserDTO.login,
                registerUserDTO.password,
                registerUserDTO.email
            )
        )
        dataStore.edit {
            it[USER_LIST] = gsonConverter.toJson(result)
        }
        updateUsers()
        return login(LoginUserDTO(registerUserDTO.login, registerUserDTO.password))
    }

    private suspend fun updateUsers() {
        users = withContext(Dispatchers.IO) {
            val jsonResult = dataStore.data.map { it[USER_LIST] }.firstOrNull()
            return@withContext if (jsonResult != null) gsonConverter.fromJson(jsonResult)
            else listOf()
        }
    }

    private suspend fun saveToken(user: User) {
        dataStore.edit {
            it[LOGGED_USER_TOKEN] = gsonConverter.toJson(user, User::class.java)
        }
    }

    private suspend fun getToken(): BaseResult<String> {
        val token = dataStore.data.map { it[LOGGED_USER_TOKEN] }.firstOrNull()
        return if (token == null) BaseResult(isSuccess = false)
        else BaseResult(data = token, isSuccess = true)
    }

    override suspend fun getLoggedUser(): BaseResult<UserInfoDTO> {
        delay(2000)
        val token = getToken().let { if (it.isSuccess) it.data else null }
        return if (token == null) BaseResult(isSuccess = false)
        else {
            val user = gsonConverter.fromJson(token, User::class.java)
            BaseResult(
                isSuccess = true,
                data = user.toDomainModel()
            )
        }
    }

    override suspend fun getUserById(id: Int): BaseResult<UserInfoDTO> {
        val user = users.find { it.id == id }
        return if (user != null) BaseResult(isSuccess = true, data = user.toDomainModel())
        else BaseResult(isSuccess = false)
    }

    override suspend fun updateUserInfo(userInfoDTO: UserInfoDTO): BaseResult<Boolean> {
        val token = getToken().let { if (it.isSuccess) it.data else null }
        return if (token == null) BaseResult(isSuccess = false)
        else {
            val user = gsonConverter.fromJson(token, User::class.java)
            users = users.map { if (it.id != user.id) it else user }
            BaseResult(isSuccess = true)
        }
    }

    override suspend fun sendComment(
        commentDTO: CommentDTO,
        receiver: UserInfoDTO
    ): BaseResult<Boolean> {
        val loggedUserData = getLoggedUser().data
        val loggedUser = users.find { it.toDomainModel() == loggedUserData }
        var isCommentSent = false
        loggedUser?.let {
            val comment = Comment(0, loggedUser, commentDTO.text, commentDTO.dateTime)
            users = users.map {
                if (it.toDomainModel() != receiver) it else {
                    isCommentSent = true
                    val user = User(
                        it.id,
                        it.login,
                        it.password,
                        it.email,
                        it.tags,
                        it.photoUrl,
                        it.comments.toMutableList().plus(comment)
                    )
                    saveToken(user)
                    user
                }
            }
        }
        if (isCommentSent) {
            saveUsers()
        }
        return BaseResult(isSuccess = isCommentSent)
    }

    private suspend fun saveUsers() {
        dataStore.edit {
            it[USER_LIST] = gsonConverter.toJson(users)
        }
    }


    override suspend fun getUsers(): BaseResult<List<UserInfoDTO>> {
        val usersInfo = users.map { it.toDomainModel() }
        return BaseResult(isSuccess = true, data = usersInfo)
    }

    private fun getNextId(): Int {
        return users.maxOfOrNull { it.id }?.plus(1) ?: START_ID
    }

    companion object {
        private val USER_LIST = stringPreferencesKey("USER_LIST")
        private val LOGGED_USER_TOKEN = stringPreferencesKey("LOGGED_USER_TOKEN")
        private const val START_ID = 1
    }
}

internal inline fun <reified T> Gson.fromJson(json: String) =
    fromJson<T>(json, object : TypeToken<T>() {}.type)