package ua.nure.chumchase.auth.data

import kotlinx.coroutines.*
import ua.nure.chumchase.auth.data.entity.LoginUser
import ua.nure.chumchase.auth.data.entity.RegisterUser
import ua.nure.chumchase.auth.domain.AuthDataSource
import ua.nure.chumchase.auth.domain.model.LoginUserDTO
import ua.nure.chumchase.auth.domain.model.RegisterUserDTO
import ua.nure.chumchase.core.base.BaseOperationResult
import ua.nure.chumchase.core.data.token.TokenManager
import ua.nure.chumchase.core.utils.getErrorMessage
import ua.nure.chumchase.core.utils.handle

class AuthDataSourceImpl(
    private val authService: AuthService,
    private val tokenManager: TokenManager,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) : AuthDataSource {
    override suspend fun login(loginUserDTO: LoginUserDTO): BaseOperationResult {
        return try {
            val response = authService.login(LoginUser(loginUserDTO.login, loginUserDTO.password))
            response.handle {
                it?.let { token ->
                    CoroutineScope(defaultDispatcher).launch {
                        tokenManager.saveToken(token.toDomainModel())
                    }
                }
            }
        } catch (exception: Exception) {
            BaseOperationResult(false, exception.getErrorMessage())
        }
    }

    override suspend fun register(registerUserDTO: RegisterUserDTO): BaseOperationResult {
        return try {
            val response = authService.register(
                RegisterUser(
                    registerUserDTO.login,
                    registerUserDTO.email,
                    registerUserDTO.password
                )
            )
            response.handle()
        } catch (exception: Exception) {
            BaseOperationResult(false, exception.getErrorMessage())
        }
    }
}