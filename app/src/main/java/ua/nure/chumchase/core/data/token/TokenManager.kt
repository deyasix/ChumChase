package ua.nure.chumchase.core.data.token

import ua.nure.chumchase.core.base.BaseResult

interface TokenManager {
    suspend fun saveToken(token: String)
    suspend fun getToken(): String?
    suspend fun isUserLogged(): BaseResult<Boolean>
}