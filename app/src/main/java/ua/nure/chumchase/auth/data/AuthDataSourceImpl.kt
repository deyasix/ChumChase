package ua.nure.chumchase.auth.data

import ua.nure.chumchase.auth.data.entity.LoginUser
import ua.nure.chumchase.auth.data.entity.RegisterUser
import ua.nure.chumchase.auth.domain.AuthDataSource
import ua.nure.chumchase.core.domain.AuthResponseMessage
import ua.nure.chumchase.auth.domain.model.LoginUserDTO
import ua.nure.chumchase.auth.domain.model.RegisterUserDTO
import ua.nure.chumchase.core.base.BaseResult
import ua.nure.chumchase.core.data.token.TokenManager
import ua.nure.chumchase.core.utils.getErrorMessage

class AuthDataSourceImpl(
    private val authService: AuthService,
    private val tokenManager: TokenManager
) : AuthDataSource {
    override suspend fun login(loginUserDTO: LoginUserDTO): BaseResult<Boolean> {
        val response = authService.login(LoginUser(loginUserDTO.login, loginUserDTO.password))
        return if (response.isSuccessful) {
            response.body()?.access_token?.let { token ->
                tokenManager.saveToken(token)
            }
            BaseResult(isSuccess = true)
        } else {
            val message = response.errorBody()?.getErrorMessage()
            BaseResult(isSuccess = false, error = AuthResponseMessage.getResponseMessage(message))
        }
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
        } else {
            val error = response.errorBody()?.getErrorMessage()
            BaseResult(isSuccess = false, error = AuthResponseMessage.getResponseMessage(error))
        }
    }
}