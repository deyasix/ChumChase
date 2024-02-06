package ua.nure.chumchase.auth.domain

import ua.nure.chumchase.auth.domain.model.LoginUserDTO
import ua.nure.chumchase.auth.domain.model.RegisterUserDTO
import ua.nure.chumchase.core.base.BaseResult

class AuthRepositoryImpl(private val authDataSource: AuthDataSource) : AuthRepository {
    override suspend fun login(loginUserDTO: LoginUserDTO): BaseResult<Boolean> {
        val result = authDataSource.login(loginUserDTO)
        return if (result.isSuccess) {
            BaseResult(isSuccess = true)
        } else {
            BaseResult(isSuccess = false)
        }
    }

    override suspend fun register(registerUserDTO: RegisterUserDTO): BaseResult<Boolean> {
        val result = authDataSource.register(registerUserDTO)
        return if (result.isSuccess) {
            return login(LoginUserDTO(registerUserDTO.login, registerUserDTO.password))
        } else BaseResult(isSuccess = false)
    }
}