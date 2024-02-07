package ua.nure.chumchase.feature.profile

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import ua.nure.chumchase.feature.profile.data.profileDataModule
import ua.nure.chumchase.feature.profile.domain.profileDomainModule
import ua.nure.chumchase.feature.profile.presentation.ProfileViewModel

val profileModule = module {
    includes(profileDataModule, profileDomainModule)
    viewModelOf(::ProfileViewModel)
}