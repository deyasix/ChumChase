package ua.nure.chumchase.auth.data

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import ua.nure.chumchase.auth.data.entity.AccessTokenDto
import ua.nure.chumchase.auth.data.entity.LoginUserDto
import ua.nure.chumchase.core.data.MessageDto
import ua.nure.chumchase.auth.data.entity.RegisterUserDto

interface AuthService {
    @POST("user/login")
    suspend fun login(
        @Body loginUserDTO: LoginUserDto
    ): Response<AccessTokenDto>

    @POST("user/register")
    suspend fun register(
        @Body registerUserDTO: RegisterUserDto
    ): Response<MessageDto>
}