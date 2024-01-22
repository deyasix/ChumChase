package ua.nure.chumchase.feature.profile.domain

import ua.nure.chumchase.auth.domain.UserDataSource
import ua.nure.chumchase.core.base.BaseResult
import ua.nure.chumchase.feature.profile.domain.model.CommentDTO
import ua.nure.chumchase.feature.profile.domain.model.UserInfoDTO

class UserInfoRepositoryFakeImpl(private val userDataSource: UserDataSource) : UserInfoRepository {

    override suspend fun getLoggedUserInfo(): BaseResult<UserInfoDTO> {
        return userDataSource.getLoggedUser()
    }

    override suspend fun getUserInfo(userId: Int): BaseResult<UserInfoDTO> {
        return userDataSource.getUserById(userId)
    }

    override suspend fun saveUserInfo(userInfoDTO: UserInfoDTO): BaseResult<Boolean> {
        return userDataSource.updateUserInfo(userInfoDTO)
    }

    override suspend fun sendComment(
        commentDTO: CommentDTO,
        userInfoDTO: UserInfoDTO
    ): BaseResult<Boolean> {
        return userDataSource.sendComment(commentDTO, userInfoDTO)
    }

}