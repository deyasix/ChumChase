package ua.nure.chumchase.feature.profile.domain

import ua.nure.chumchase.core.base.BaseResult
import ua.nure.chumchase.feature.profile.domain.model.ProfileDTO

interface ProfileDataSource {
    suspend fun getMyProfile(): BaseResult<ProfileDTO>
    suspend fun getProfile(uid: String): BaseResult<ProfileDTO>
}