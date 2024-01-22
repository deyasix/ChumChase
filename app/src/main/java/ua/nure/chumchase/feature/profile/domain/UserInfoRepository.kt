package ua.nure.chumchase.feature.profile.domain

import ua.nure.chumchase.core.base.BaseResult
import ua.nure.chumchase.feature.profile.domain.model.CommentDTO
import ua.nure.chumchase.feature.profile.domain.model.UserInfoDTO

interface UserInfoRepository {
    suspend fun getLoggedUserInfo(): BaseResult<UserInfoDTO>

    suspend fun getUserInfo(userId: Int): BaseResult<UserInfoDTO>
    suspend fun saveUserInfo(userInfoDTO: UserInfoDTO): BaseResult<Boolean>

    suspend fun sendComment(commentDTO: CommentDTO, userInfoDTO: UserInfoDTO): BaseResult<Boolean>

}