package ua.nure.chumchase.core.data.token

import ua.nure.chumchase.core.base.BaseDataResult
import ua.nure.chumchase.core.base.BaseOperationResult

interface SessionManager {
    suspend fun saveToken(token: AccessTokenDTO)
    suspend fun getAccessToken(): BaseDataResult<String>
    suspend fun getRefreshToken(): BaseDataResult<String>
    suspend fun isUserLogged(): BaseOperationResult
    suspend fun isTokenExpired(): Boolean
}