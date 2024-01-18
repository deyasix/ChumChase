package ua.nure.chumchase.auth.domain

import ua.nure.chumchase.auth.domain.model.LoginUserDTO
import ua.nure.chumchase.core.base.BaseResult

class LoginUseCase(private val userDataSource: UserDataSource) {
    suspend fun execute(login: String?, password: String?): BaseResult<Boolean> {
        return if (login == null || password == null) BaseResult(
            isSuccess = false,
            error = BaseFieldErrors.EMPTY
        )
        else userDataSource.login(LoginUserDTO(login, password))
    }
}