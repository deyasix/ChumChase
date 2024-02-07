package ua.nure.chumchase.feature.profile.data

import ua.nure.chumchase.core.domain.AuthResponseMessage
import ua.nure.chumchase.core.base.BaseResult
import ua.nure.chumchase.core.data.token.TokenManager
import ua.nure.chumchase.core.utils.getErrorMessage
import ua.nure.chumchase.feature.profile.domain.ProfileDataSource
import ua.nure.chumchase.feature.profile.domain.model.ProfileDTO

class ProfileDataSourceImpl(
    private val tokenManager: TokenManager,
    private val profileService: ProfileService
) : ProfileDataSource {
    override suspend fun getMyProfile(): BaseResult<ProfileDTO> {
        val token = tokenManager.getToken()
        return if (token == null) BaseResult(isSuccess = false)
        else {
            val response = profileService.getMyProfile(token)
            val profile = response.body()
            profile?.let {
                return BaseResult(
                    isSuccess = true,
                    data = ProfileDTO(it.uid, it.login, it.email, it.photo_url, it.tag_list)
                )
            }
            val message = response.errorBody()?.getErrorMessage()
            BaseResult(isSuccess = false, error = AuthResponseMessage.getResponseMessage(message))
        }
    }

    override suspend fun getProfile(uid: String): BaseResult<ProfileDTO> {
        val response = profileService.getProfile(uid)
        val profile = response.body()
        profile?.let {
            return BaseResult(
                isSuccess = true,
                data = ProfileDTO(it.uid, it.login, it.email, it.photo_url, it.tag_list)
            )
        }
        val message = response.errorBody()?.getErrorMessage()
        return BaseResult(
            isSuccess = false,
            error = AuthResponseMessage.getResponseMessage(message)
        )
    }
}