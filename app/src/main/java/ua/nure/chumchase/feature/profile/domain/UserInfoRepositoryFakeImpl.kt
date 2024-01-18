package ua.nure.chumchase.feature.profile.domain

import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ua.nure.chumchase.auth.data.entity.User
import ua.nure.chumchase.auth.data.fromJson
import ua.nure.chumchase.auth.domain.UserDataSource
import ua.nure.chumchase.core.base.BaseResult
import ua.nure.chumchase.feature.profile.domain.model.UserInfoDTO

class UserInfoRepositoryFakeImpl(private val userDataSource: UserDataSource) : UserInfoRepository {

    init {
        CoroutineScope(Dispatchers.IO).launch {
//            val sample = UserInfo(
//                "login",
//                listOf("Music", "Sport", "Programming", "Art", "Nature"),
//                "https://as1.ftcdn.net/v2/jpg/01/70/46/30/1000_F_170463042_lZMoeGdH6wsiu4yczEqo13NFeFGvx72Y.jpg"
//            )
            //val receivedData = getUsersInfo()
            //receivedData.add(sample)

        }
    }


    override suspend fun getLoggedUserInfo(): BaseResult<UserInfoDTO> {
        return userDataSource.getLoggedUser()
    }

    override suspend fun getUserInfo(userId: Int): BaseResult<UserInfoDTO> {
        return userDataSource.getUserById(userId)
    }

    override suspend fun saveUserInfo(userInfoDTO: UserInfoDTO): BaseResult<Boolean> {
        return userDataSource.updateUserInfo(userInfoDTO)
    }

}