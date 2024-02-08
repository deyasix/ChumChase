package ua.nure.chumchase.feature.profile.domain

import ua.nure.chumchase.core.base.BaseDataResult
import ua.nure.chumchase.feature.profile.domain.model.ProfileDTO

interface ProfileDataSource {
    suspend fun getMyProfile(): BaseDataResult<ProfileDTO>
    suspend fun getProfile(uid: String): BaseDataResult<ProfileDTO>
}