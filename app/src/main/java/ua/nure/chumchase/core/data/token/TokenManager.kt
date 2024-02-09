package ua.nure.chumchase.core.data.token

import ua.nure.chumchase.core.base.BaseDataResult
import ua.nure.chumchase.core.base.BaseOperationResult

interface TokenManager {
    suspend fun saveToken(token: AccessTokenDTO)
    suspend fun getAccessToken(): BaseDataResult<String>
    suspend fun isUserLogged(): BaseOperationResult
}