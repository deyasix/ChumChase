package ua.nure.chumchase.auth.domain

import ua.nure.chumchase.auth.domain.model.User
import ua.nure.chumchase.base.BaseResult

interface UserDataSource {

    suspend fun login(login: String, password: String): BaseResult<Boolean>
    suspend fun register(user: User): BaseResult<Boolean>

}