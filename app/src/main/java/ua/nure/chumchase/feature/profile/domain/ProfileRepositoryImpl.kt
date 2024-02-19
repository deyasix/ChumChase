package ua.nure.chumchase.feature.profile.domain

import ua.nure.chumchase.core.base.BaseDataResult
import ua.nure.chumchase.core.base.BaseOperationResult
import ua.nure.chumchase.feature.profile.domain.model.Profile

class ProfileRepositoryImpl(private val profileDataSource: ProfileDataSource) : ProfileRepository {

    override suspend fun getLoggedUserInfo(): BaseDataResult<Profile> {
        return profileDataSource.getMyProfile()
    }

    override suspend fun getUserInfo(userId: String): BaseDataResult<Profile> {
        return profileDataSource.getProfile(userId)
    }

    override suspend fun saveUserInfo(profile: Profile): BaseDataResult<Boolean> {
        //return authRepository.updateUserInfo(userInfoDTO)
        return BaseDataResult(isSuccess = false)
    }

    override suspend fun isMyProfile(uid: String): BaseOperationResult {
        return profileDataSource.isMyProfile(uid)
    }

}