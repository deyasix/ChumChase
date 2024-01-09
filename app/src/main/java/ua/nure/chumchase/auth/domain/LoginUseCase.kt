package ua.nure.chumchase.auth.domain

import ua.nure.chumchase.base.BaseResult

class LoginUseCase(private val userDataSource: UserDataSource) {
    suspend fun execute(login: String?, password: String?): BaseResult<Boolean> {
        return if (login == null || password == null) BaseResult(
            isSuccess = false,
            error = BaseFieldErrors.EMPTY
        )
        else userDataSource.login(login, password)
    }
}