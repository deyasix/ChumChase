package ua.nure.chumchase.auth.domain

import ua.nure.chumchase.auth.domain.model.LoginUser
import ua.nure.chumchase.auth.domain.model.RegisterUser
import ua.nure.chumchase.core.base.BaseOperationResult

interface AuthRepository {

    suspend fun login(loginUser: LoginUser): BaseOperationResult
    suspend fun register(registerUser: RegisterUser): BaseOperationResult

}