package ua.nure.chumchase.auth.domain

import ua.nure.chumchase.auth.domain.model.LoginUserDTO
import ua.nure.chumchase.auth.domain.model.RegisterUserDTO
import ua.nure.chumchase.core.base.BaseResult
import ua.nure.chumchase.feature.profile.domain.model.UserInfoDTO

interface UserDataSource {

    suspend fun login(loginUserDTO: LoginUserDTO): BaseResult<Boolean>
    suspend fun register(registerUserDTO: RegisterUserDTO): BaseResult<Boolean>
    suspend fun getUsers(): BaseResult<List<UserInfoDTO>>
    suspend fun getLoggedUser(): BaseResult<UserInfoDTO>
    suspend fun getUserById(id: Int): BaseResult<UserInfoDTO>
    suspend fun updateUserInfo(userInfoDTO: UserInfoDTO): BaseResult<Boolean>

}