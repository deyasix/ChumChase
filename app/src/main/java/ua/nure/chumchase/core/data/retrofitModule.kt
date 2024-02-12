package ua.nure.chumchase.core.data

import kotlinx.coroutines.Dispatchers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ua.nure.chumchase.BuildConfig
import ua.nure.chumchase.core.data.token.TokenService
import ua.nure.chumchase.core.data.token.sessionManagerModule

val retrofitModule = module {
    includes(sessionManagerModule)
    single { Dispatchers.IO }
    singleOf(::AuthInterceptor) bind Interceptor::class
    singleOf(::getRetrofitClient)
    singleOf(::getTokenService)
}

fun getRetrofitClient(interceptor: Interceptor): Retrofit {
    val httpClient = OkHttpClient().newBuilder().addInterceptor(interceptor)
    if (BuildConfig.DEBUG) {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS)
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        httpClient.addInterceptor(logging)
    }
    return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).client(httpClient.build()).build()
}

private fun getTokenService(retrofit: Retrofit): TokenService {
    return retrofit.create(TokenService::class.java)
}