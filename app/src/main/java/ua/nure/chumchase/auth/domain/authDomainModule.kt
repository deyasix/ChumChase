package ua.nure.chumchase.auth.domain

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val authDomainModule = module {
    singleOf(::AuthRepositoryImpl) bind AuthRepository::class
}