package ua.nure.chumchase.core.data.token

import ua.nure.chumchase.core.base.BaseDataResult

interface TokenManager {
    suspend fun saveToken(token: String)
    suspend fun getToken(): String?
    suspend fun isUserLogged(): BaseDataResult<Boolean>
}