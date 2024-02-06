package ua.nure.chumchase.auth.domain

import ua.nure.chumchase.auth.domain.model.LoginUserDTO
import ua.nure.chumchase.auth.domain.model.RegisterUserDTO
import ua.nure.chumchase.core.base.BaseResult

interface AuthRepository {

    suspend fun login(loginUserDTO: LoginUserDTO): BaseResult<Boolean>
    suspend fun register(registerUserDTO: RegisterUserDTO): BaseResult<Boolean>

}