package ua.nure.chumchase.feature.profile.data

import ua.nure.chumchase.core.base.BaseDataResult
import ua.nure.chumchase.core.data.token.TokenManager
import ua.nure.chumchase.core.utils.getErrorMessage
import ua.nure.chumchase.core.utils.handleData
import ua.nure.chumchase.feature.profile.domain.ProfileDataSource
import ua.nure.chumchase.feature.profile.domain.model.ProfileDTO

class ProfileDataSourceImpl(
    private val tokenManager: TokenManager,
    private val profileService: ProfileService
) : ProfileDataSource {
    override suspend fun getMyProfile(): BaseDataResult<ProfileDTO> {
        return try {
            val result = tokenManager.getAccessToken()
            if (!result.isSuccess || result.data == null) BaseDataResult(
                isSuccess = false,
                error = result.error
            )
            else {
                val response = profileService.getMyProfile(result.data)
                response.handleData()
            }
        } catch (exception: Exception) {
            BaseDataResult(
                false, exception.getErrorMessage()
            )
        }
    }

    override suspend fun getProfile(uid: String): BaseDataResult<ProfileDTO> {
        return try {
            val response = profileService.getProfile(uid)
            response.handleData()
        } catch (exception: Exception) {
            BaseDataResult(
                false, exception.getErrorMessage()
            )
        }
    }
}