package ua.nure.chumchase.feature.profile.domain

import ua.nure.chumchase.auth.domain.AuthRepository
import ua.nure.chumchase.core.base.BaseResult
import ua.nure.chumchase.feature.profile.domain.model.CommentDTO
import ua.nure.chumchase.feature.profile.domain.model.UserInfoDTO

class UserInfoRepositoryFakeImpl(private val authRepository: AuthRepository) : UserInfoRepository {

    override suspend fun getLoggedUserInfo(): BaseResult<UserInfoDTO> {
        //return authRepository.getLoggedUser()
        return BaseResult(isSuccess = false)
    }

    override suspend fun getUserInfo(userId: Int): BaseResult<UserInfoDTO> {
        //return authRepository.getUserById(userId)
        return BaseResult(isSuccess = false)
    }

    override suspend fun saveUserInfo(userInfoDTO: UserInfoDTO): BaseResult<Boolean> {
        //return authRepository.updateUserInfo(userInfoDTO)
        return BaseResult(isSuccess = false)
    }

    override suspend fun sendComment(
        commentDTO: CommentDTO,
        userInfoDTO: UserInfoDTO
    ): BaseResult<Boolean> {
        //return authRepository.sendComment(commentDTO, userInfoDTO)
        return BaseResult(isSuccess = false)
    }

}