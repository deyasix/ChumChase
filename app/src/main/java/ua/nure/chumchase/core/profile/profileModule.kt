package ua.nure.chumchase.core.profile

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ua.nure.chumchase.auth.dataStore
import ua.nure.chumchase.core.profile.data.UserDataSourceFakeImpl
import ua.nure.chumchase.core.profile.domain.UserDataSource
import ua.nure.chumchase.core.profile.presentation.ProfileViewModel
import ua.nure.chumchase.core.profile.domain.GetUserInfoUseCase

val profileModule = module {
    single { androidContext().dataStore }
    singleOf(::UserDataSourceFakeImpl) bind UserDataSource::class
    singleOf(::GetUserInfoUseCase)
    viewModelOf(::ProfileViewModel)
}