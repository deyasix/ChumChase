package ua.nure.chumchase.core.data.token

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import timber.log.Timber
import ua.nure.chumchase.core.base.BaseDataResult
import ua.nure.chumchase.core.base.BaseOperationResult
import ua.nure.chumchase.core.domain.ErrorMessage
import ua.nure.chumchase.core.domain.OperationStatusMessage
import ua.nure.chumchase.core.utils.getErrorMessage
import ua.nure.chumchase.core.utils.handleData

class TokenManagerImpl(
    private val dataStore: DataStore<Preferences>,
    private val tokenService: TokenService,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) : TokenManager {
    override suspend fun saveToken(token: AccessTokenDTO) {
        dataStore.edit {
            it[ACCESS_TOKEN] = "Bearer ${token.accessToken}"
            it[REFRESH_TOKEN] = "Bearer ${token.refreshToken}"
            it[EXPIRATION_DATE] = System.currentTimeMillis() + token.expiresIn
        }
    }

    override suspend fun getAccessToken(): BaseDataResult<String> {
        return if (isTokenExpired()) {
            Timber.d("token is expired")
            getRefreshedToken()
        } else {
            BaseDataResult(isSuccess = true, data = dataStore.data.first()[ACCESS_TOKEN])
        }
    }

    private suspend fun getRefreshedToken(): BaseDataResult<String> {
        var token: String? = null
        var isSuccess = false
        var error: ErrorMessage? = OperationStatusMessage.UNAUTHORIZED
        val refreshToken = dataStore.data.first()[REFRESH_TOKEN]
        if (refreshToken != null) {
            try {
                val response = tokenService.refreshToken(refreshToken)
                val result = response.handleData { accessToken ->
                    accessToken?.let {
                        token = "Bearer ${it.accessToken}"
                        CoroutineScope(defaultDispatcher).launch {
                            saveToken(it.toDomainModel())
                        }
                    }
                }
                isSuccess = result.isSuccess
                error = result.error
            } catch (exception: Exception) {
                isSuccess = false
                error = exception.getErrorMessage()
            }
        }
        return BaseDataResult(isSuccess = isSuccess, data = token, error = error)
    }

    override suspend fun isUserLogged(): BaseOperationResult {
        val result = getAccessToken()
        return BaseOperationResult(isSuccess = result.data != null, error = result.error)
    }

    private suspend fun isTokenExpired(): Boolean {
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