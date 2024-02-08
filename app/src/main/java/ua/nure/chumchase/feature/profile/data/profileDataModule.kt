package ua.nure.chumchase.feature.profile.data

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import ua.nure.chumchase.feature.profile.domain.ProfileDataSource

val profileDataModule = module {
    singleOf(::getProfileService)
    singleOf(::ProfileDataSourceImpl) bind ProfileDataSource::class
}

private fun getProfileService(retrofit: Retrofit): ProfileService {
    return retrofit.create(ProfileService::class.java)
}