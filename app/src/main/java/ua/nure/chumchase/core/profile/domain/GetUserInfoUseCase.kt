package ua.nure.chumchase.core.profile.domain

import ua.nure.chumchase.core.base.BaseResult
import ua.nure.chumchase.core.profile.domain.model.UserInfo

class GetUserInfoUseCase(private val userDataSource: UserDataSource) {
    suspend fun execute(): BaseResult<UserInfo> {
        return userDataSource.getUserInfo()
    }
}