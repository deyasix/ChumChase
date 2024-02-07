package ua.nure.chumchase.feature.profile.domain

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val profileDomainModule = module {
    singleOf(::ProfileRepositoryImpl) bind ProfileRepository::class
}