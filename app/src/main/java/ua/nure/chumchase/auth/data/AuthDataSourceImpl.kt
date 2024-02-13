package ua.nure.chumchase.auth.data

import kotlinx.coroutines.*
import ua.nure.chumchase.auth.domain.AuthDataSource
import ua.nure.chumchase.auth.domain.model.LoginUser
import ua.nure.chumchase.auth.domain.model.RegisterUser
import ua.nure.chumchase.core.base.BaseOperationResult
import ua.nure.chumchase.core.data.token.SessionManager
import ua.nure.chumchase.core.utils.getErrorMessage
import ua.nure.chumchase.core.utils.handle

class AuthDataSourceImpl(
    private val authService: AuthService,
    private val sessionManager: SessionManager,
    private val accessTokenDtoMapper: AccessTokenDtoMapper,
    private val registerUserDtoMapper: RegisterUserDtoMapper,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) : AuthDataSource {
    override suspend fun login(loginUser: LoginUser): BaseOperationResult {
        return try {
            val response = authService.login(
                ua.nure.chumchase.auth.data.entity.LoginUserDto(
                    loginUser.login,
                    loginUser.password
                )
            )
            response.handle {
                it?.let { token ->
                    CoroutineScope(defaultDispatcher).launch {
                        sessionManager.saveToken(accessTokenDtoMapper.mapToDomainModel(token))
                    }
                }
            }
        } catch (exception: Exception) {
            BaseOperationResult(false, exception.getErrorMessage())
        }
    }

    override suspend fun register(registerUser: RegisterUser): BaseOperationResult {
        return try {
            val response = authService.register(
                registerUserDtoMapper.mapFromDomainModel(registerUser)
            )
            response.handle()
        } catch (exception: Exception) {
            BaseOperationResult(false, exception.getErrorMessage())
        }
    }
}