package ua.nure.chumchase.auth

import org.koin.dsl.module
import ua.nure.chumchase.auth.data.authDataModule
import ua.nure.chumchase.auth.domain.authDomainModule
import ua.nure.chumchase.auth.presentation.authPresentationModule

val authModule = module {
    includes(authPresentationModule, authDomainModule, authDataModule)
}