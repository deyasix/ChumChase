package ua.nure.chumchase.feature.profile.domain

import ua.nure.chumchase.core.base.BaseDataResult
import ua.nure.chumchase.feature.profile.domain.model.CommentDTO
import ua.nure.chumchase.feature.profile.domain.model.ProfileDTO

class ProfileRepositoryImpl(private val profileDataSource: ProfileDataSource) : ProfileRepository {

    override suspend fun getLoggedUserInfo(): BaseDataResult<ProfileDTO> {
        return profileDataSource.getMyProfile()
    }

    override suspend fun getUserInfo(userId: String): BaseDataResult<ProfileDTO> {
        return profileDataSource.getProfile(userId)
    }

    override suspend fun saveUserInfo(profileDTO: ProfileDTO): BaseDataResult<Boolean> {
        //return authRepository.updateUserInfo(userInfoDTO)
        return BaseDataResult(isSuccess = false)
    }

    override suspend fun sendComment(
        commentDTO: CommentDTO,
        profileDTO: ProfileDTO
    ): BaseDataResult<Boolean> {
        //return authRepository.sendComment(commentDTO, userInfoDTO)
        return BaseDataResult(isSuccess = false)
    }

}