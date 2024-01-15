package ua.nure.chumchase.core.profile.domain

import ua.nure.chumchase.core.base.BaseResult
import ua.nure.chumchase.core.profile.domain.model.UserInfo

interface UserDataSource {

    suspend fun getUserInfo(): BaseResult<UserInfo>

}