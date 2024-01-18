package ua.nure.chumchase.feature.profile

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ua.nure.chumchase.auth.dataStore
import ua.nure.chumchase.feature.profile.domain.UserInfoRepository
import ua.nure.chumchase.feature.profile.domain.UserInfoRepositoryFakeImpl
import ua.nure.chumchase.feature.profile.presentation.ProfileViewModel

val profileModule = module {
    single { androidContext().dataStore }
    singleOf(::UserInfoRepositoryFakeImpl) bind UserInfoRepository::class
    viewModelOf(::ProfileViewModel)
}