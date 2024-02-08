package ua.nure.chumchase.feature.profile.domain

import ua.nure.chumchase.core.base.BaseDataResult
import ua.nure.chumchase.feature.profile.domain.model.CommentDTO
import ua.nure.chumchase.feature.profile.domain.model.ProfileDTO

interface ProfileRepository {
    suspend fun getLoggedUserInfo(): BaseDataResult<ProfileDTO>
    suspend fun getUserInfo(userId: String): BaseDataResult<ProfileDTO>
    suspend fun saveUserInfo(profileDTO: ProfileDTO): BaseDataResult<Boolean>
    suspend fun sendComment(commentDTO: CommentDTO, profileDTO: ProfileDTO): BaseDataResult<Boolean>
}