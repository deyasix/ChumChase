package ua.nure.chumchase.feature.profile.domain

import ua.nure.chumchase.core.base.BaseResult
import ua.nure.chumchase.feature.profile.domain.model.CommentDTO
import ua.nure.chumchase.feature.profile.domain.model.ProfileDTO

class ProfileRepositoryImpl(private val profileDataSource: ProfileDataSource) : ProfileRepository {

    override suspend fun getLoggedUserInfo(): BaseResult<ProfileDTO> {
        return profileDataSource.getMyProfile()
    }

    override suspend fun getUserInfo(userId: String): BaseResult<ProfileDTO> {
        return profileDataSource.getProfile(userId)
    }

    override suspend fun saveUserInfo(profileDTO: ProfileDTO): BaseResult<Boolean> {
        //return authRepository.updateUserInfo(userInfoDTO)
        return BaseResult(isSuccess = false)
    }

    override suspend fun sendComment(
        commentDTO: CommentDTO,
        profileDTO: ProfileDTO
    ): BaseResult<Boolean> {
        //return authRepository.sendComment(commentDTO, userInfoDTO)
        return BaseResult(isSuccess = false)
    }

}