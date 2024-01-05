package ua.nure.chumchase.di.module

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ua.nure.chumchase.auth.domain.LoginUseCase
import ua.nure.chumchase.auth.presentation.LoginViewModel
import ua.nure.chumchase.auth.presentation.RegisterViewModel

val appModule = module {
    singleOf(::LoginUseCase)
    viewModelOf(::LoginViewModel)
    viewModelOf(::RegisterViewModel)
}