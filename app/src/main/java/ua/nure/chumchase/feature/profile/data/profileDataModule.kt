package ua.nure.chumchase.feature.profile.data

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import ua.nure.chumchase.feature.profile.domain.CommentDataSource
import ua.nure.chumchase.feature.profile.domain.ProfileDataSource
import ua.nure.chumchase.feature.profile.domain.SendCommentUseCase

val profileDataModule = module {
    singleOf(::getProfileService)
    singleOf(::CommentDtoMapper)
    singleOf(::ProfileDtoMapper)
    singleOf(::PlainCommentDtoMapper)
    singleOf(::ProfileDataSourceImpl) bind ProfileDataSource::class
    singleOf(::CommentDataSourceImpl) bind CommentDataSource::class
    singleOf(::getCommentService)
    singleOf(::SendCommentUseCase)
}

private fun getProfileService(retrofit: Retrofit): ProfileService {
    return retrofit.create(ProfileService::class.java)
}

private fun getCommentService(retrofit: Retrofit): CommentService {
    return retrofit.create(CommentService::class.java)
}