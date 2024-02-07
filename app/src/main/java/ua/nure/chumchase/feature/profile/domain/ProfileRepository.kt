package ua.nure.chumchase.feature.profile.domain

import ua.nure.chumchase.core.base.BaseResult
import ua.nure.chumchase.feature.profile.domain.model.CommentDTO
import ua.nure.chumchase.feature.profile.domain.model.ProfileDTO

interface ProfileRepository {
    suspend fun getLoggedUserInfo(): BaseResult<ProfileDTO>
    suspend fun getUserInfo(userId: String): BaseResult<ProfileDTO>
    suspend fun saveUserInfo(profileDTO: ProfileDTO): BaseResult<Boolean>
    suspend fun sendComment(commentDTO: CommentDTO, profileDTO: ProfileDTO): BaseResult<Boolean>
}