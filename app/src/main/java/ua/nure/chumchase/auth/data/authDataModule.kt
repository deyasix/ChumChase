package ua.nure.chumchase.auth.data

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import ua.nure.chumchase.auth.domain.AuthDataSource

val authDataModule = module {
    singleOf(::getAuthService)
    singleOf(::AccessTokenDtoMapper)
    singleOf(::RegisterUserDtoMapper)
    singleOf(::AuthDataSourceImpl) bind AuthDataSource::class
}

private fun getAuthService(retrofit: Retrofit): AuthService {
    return retrofit.create(AuthService::class.java)
}