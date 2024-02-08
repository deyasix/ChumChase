package ua.nure.chumchase.auth.domain

import ua.nure.chumchase.auth.domain.model.LoginUserDTO
import ua.nure.chumchase.auth.domain.model.RegisterUserDTO
import ua.nure.chumchase.core.base.BaseOperationResult

class AuthRepositoryImpl(private val authDataSource: AuthDataSource) : AuthRepository {
    override suspend fun login(loginUserDTO: LoginUserDTO): BaseOperationResult {
        return authDataSource.login(loginUserDTO)
    }

    override suspend fun register(registerUserDTO: RegisterUserDTO): BaseOperationResult {
        val result = authDataSource.register(registerUserDTO)
        return if (result.isSuccess) {
            login(LoginUserDTO(registerUserDTO.login, registerUserDTO.password))
        } else {
            result
        }
    }
}