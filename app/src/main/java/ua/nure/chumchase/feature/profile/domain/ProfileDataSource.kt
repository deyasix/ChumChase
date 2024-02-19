package ua.nure.chumchase.feature.profile.domain

import ua.nure.chumchase.core.base.BaseDataResult
import ua.nure.chumchase.core.base.BaseOperationResult
import ua.nure.chumchase.feature.profile.domain.model.Profile

interface ProfileDataSource {
    suspend fun getMyProfile(): BaseDataResult<Profile>
    suspend fun getProfile(uid: String): BaseDataResult<Profile>
    suspend fun isMyProfile(uid: String): BaseOperationResult
}