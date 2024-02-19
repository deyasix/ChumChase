package ua.nure.chumchase.feature.profile.data

import timber.log.Timber
import ua.nure.chumchase.core.base.BaseDataResult
import ua.nure.chumchase.core.base.BaseOperationResult
import ua.nure.chumchase.core.utils.getErrorMessage
import ua.nure.chumchase.core.utils.handle
import ua.nure.chumchase.core.utils.handleData
import ua.nure.chumchase.feature.profile.domain.ProfileDataSource
import ua.nure.chumchase.feature.profile.domain.model.Profile

class ProfileDataSourceImpl(
    private val profileService: ProfileService,
    private val profileDtoMapper: ProfileDtoMapper
) : ProfileDataSource {
    override suspend fun getMyProfile(): BaseDataResult<Profile> {
        return try {
            val response = profileService.getMyProfile()
            response.handleData(profileDtoMapper)
        } catch (exception: Exception) {
            BaseDataResult(false, exception.getErrorMessage())
        }
    }

    override suspend fun getProfile(uid: String): BaseDataResult<Profile> {
        return try {
            val response = profileService.getProfile(uid)
            response.handleData(profileDtoMapper)
        } catch (exception: Exception) {
            Timber.d(exception.message)
            BaseDataResult(false, exception.getErrorMessage())
        }
    }

    override suspend fun isMyProfile(uid: String): BaseOperationResult {
        return try {
            val response = profileService.isMyProfile(uid)
            return response.handle()
        } catch (exception: Exception) {
            BaseOperationResult(false, exception.getErrorMessage())
        }
    }
}