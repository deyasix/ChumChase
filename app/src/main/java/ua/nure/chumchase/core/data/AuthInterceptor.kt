package ua.nure.chumchase.core.data

import kotlinx.coroutines.*
import okhttp3.Interceptor
import okhttp3.Response
import org.koin.java.KoinJavaComponent.inject
import timber.log.Timber
import ua.nure.chumchase.core.data.token.AccessTokenDTO
import ua.nure.chumchase.core.data.token.SessionManager
import ua.nure.chumchase.core.data.token.TokenService
import ua.nure.chumchase.core.utils.handleData

class AuthInterceptor(
    private val sessionManager: SessionManager,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) : Interceptor {

    private val tokenService by inject<TokenService>(TokenService::class.java)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        return try {
            val accessToken = runBlocking { getAccessToken() }
            accessToken?.let {
                request = request.newBuilder()
                    .header("Authorization", "Bearer $it")
                    .build()
            }
            request = request.newBuilder().addHeader("Content-Type", "application/json").build()
            chain.proceed(request)
        } catch (exception: Exception) {
            Timber.d(exception.message)
            chain.proceed(request)
        }
    }

    private suspend fun getAccessToken(): String? {
        val accessTokenResult = sessionManager.getAccessToken()
        var accessToken = accessTokenResult.data
        if (accessTokenResult.isSuccess && sessionManager.isTokenExpired()) {
            val refreshToken = sessionManager.getRefreshToken()
            if (refreshToken.data != null) {
                val response = tokenService.refreshToken("Bearer ${refreshToken.data}")
                response.handleData {
                    it?.let {
                        CoroutineScope(defaultDispatcher).launch {
                            sessionManager.saveToken(
                                AccessTokenDTO(
                                    it.accessToken,
                                    it.refreshToken,
                                    it.expiresIn
                                )
                            )
                        }
                    }
                    accessToken = it?.accessToken
                }
            }
        }
        return accessToken
    }
}