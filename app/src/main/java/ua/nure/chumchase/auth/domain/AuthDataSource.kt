package ua.nure.chumchase.auth.domain

import ua.nure.chumchase.auth.domain.model.LoginUserDTO
import ua.nure.chumchase.auth.domain.model.RegisterUserDTO
import ua.nure.chumchase.core.base.BaseOperationResult

interface AuthDataSource {
    suspend fun login(loginUserDTO: LoginUserDTO): BaseOperationResult
    suspend fun register(registerUserDTO: RegisterUserDTO): BaseOperationResult
}