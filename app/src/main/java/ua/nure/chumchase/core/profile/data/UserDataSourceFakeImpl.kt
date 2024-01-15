package ua.nure.chumchase.core.profile.data

import kotlinx.coroutines.delay
import ua.nure.chumchase.core.base.BaseResult
import ua.nure.chumchase.core.profile.domain.UserDataSource
import ua.nure.chumchase.core.profile.domain.model.UserInfo

class UserDataSourceFakeImpl: UserDataSource {
    override suspend fun getUserInfo(): BaseResult<UserInfo> {
        delay(1000)
        val userInfo = UserInfo(
            "login",
            listOf("Music", "Sport", "Programming", "Art", "Nature"),
            "https://as1.ftcdn.net/v2/jpg/01/70/46/30/1000_F_170463042_lZMoeGdH6wsiu4yczEqo13NFeFGvx72Y.jpg"
        )
        return BaseResult(isSuccess = true, data = userInfo)
    }
}