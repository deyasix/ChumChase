package ua.nure.chumchase.auth.domain

import ua.nure.chumchase.auth.domain.model.LoginUser
import ua.nure.chumchase.auth.domain.model.RegisterUser
import ua.nure.chumchase.core.base.BaseOperationResult

class AuthRepositoryImpl(private val authDataSource: AuthDataSource) : AuthRepository {
    override suspend fun login(loginUser: LoginUser): BaseOperationResult {
        return authDataSource.login(loginUser)
    }

    override suspend fun register(registerUser: RegisterUser): BaseOperationResult {
        val result = authDataSource.register(registerUser)
        return if (result.isSuccess) {
            login(LoginUser(registerUser.login, registerUser.password))
        } else {
            result
        }
    }
}