package ua.nure.chumchase.core.data.token

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import kotlinx.coroutines.flow.first
import ua.nure.chumchase.core.base.BaseDataResult
import ua.nure.chumchase.core.base.BaseOperationResult

class SessionManagerImpl(
    private val dataStore: DataStore<Preferences>
) : SessionManager {
    override suspend fun saveToken(token: AccessToken) {
        dataStore.edit {
            it[ACCESS_TOKEN] = token.accessToken
            it[REFRESH_TOKEN] = token.refreshToken
            it[EXPIRATION_DATE] = System.currentTimeMillis() + token.expiresIn
        }
    }

    override suspend fun getAccessToken(): BaseDataResult<String> {
        val token = dataStore.data.first()[ACCESS_TOKEN]
        return BaseDataResult(isSuccess = token != null, data = token)
    }

    override suspend fun getRefreshToken(): BaseDataResult<String> {
        val refreshToken = dataStore.data.first()[REFRESH_TOKEN]
        return BaseDataResult(isSuccess = refreshToken != null, data = refreshToken)
    }

    override suspend fun isUserLogged(): BaseOperationResult {
        val result = getAccessToken()
        return BaseOperationResult(isSuccess = result.data != null, error = result.error)
    }

    override suspend fun isTokenExpired(): Boolean {
        val expirationDate = dataStore.data.first()[EXPIRATION_DATE]
        return if (expirationDate == null) false
        else System.currentTimeMillis() >= expirationDate
    }

    companion object {
        private val ACCESS_TOKEN = stringPreferencesKey("ACCESS_TOKEN")
        private val REFRESH_TOKEN = stringPreferencesKey("REFRESH_TOKEN")
        private val EXPIRATION_DATE = longPreferencesKey("EXPIRATION_DATE")
    }
}