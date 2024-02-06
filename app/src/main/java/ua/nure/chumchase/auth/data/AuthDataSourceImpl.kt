package ua.nure.chumchase.auth.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import ua.nure.chumchase.auth.data.entity.LoginUser
import ua.nure.chumchase.auth.data.entity.RegisterUser
import ua.nure.chumchase.auth.domain.AuthDataSource
import ua.nure.chumchase.auth.domain.model.LoginUserDTO
import ua.nure.chumchase.auth.domain.model.RegisterUserDTO
import ua.nure.chumchase.core.base.BaseResult

class AuthDataSourceImpl(
    private val authService: AuthService,
    private val dataStore: DataStore<Preferences>
) : AuthDataSource {
    override suspend fun login(loginUserDTO: LoginUserDTO): BaseResult<Boolean> {
        val response = authService.login(LoginUser(loginUserDTO.login, loginUserDTO.password))
        return if (response.isSuccessful) {
            response.body()?.access_token?.let { token ->
                dataStore.edit {
                    it[LOGGED_USER_TOKEN] = token
                }
            }
            BaseResult(isSuccess = true)
        } else BaseResult(isSuccess = false)
    }

    override suspend fun register(registerUserDTO: RegisterUserDTO): BaseResult<Boolean> {
        val response = authService.register(
            RegisterUser(
                registerUserDTO.login,
                registerUserDTO.email,
                registerUserDTO.password
            )
        )
        return if (response.isSuccessful) {
            BaseResult(isSuccess = true)
        } else BaseResult(isSuccess = false)
    }

    companion object {
        private val LOGGED_USER_TOKEN = stringPreferencesKey("LOGGED_USER_TOKEN")
    }
}