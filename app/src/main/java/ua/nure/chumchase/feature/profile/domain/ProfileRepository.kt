package ua.nure.chumchase.feature.profile.domain

import ua.nure.chumchase.core.base.BaseDataResult
import ua.nure.chumchase.feature.profile.domain.model.Profile

interface ProfileRepository {
    suspend fun getLoggedUserInfo(): BaseDataResult<Profile>
    suspend fun getUserInfo(userId: String): BaseDataResult<Profile>
    suspend fun saveUserInfo(profile: Profile): BaseDataResult<Boolean>
}