package ua.nure.chumchase.auth.domain

import kotlinx.coroutines.delay
import ua.nure.chumchase.auth.domain.model.User
import ua.nure.chumchase.base.BaseResult

class RegisterUseCase(private val userDataSource: UserDataSource) {
    suspend fun execute(login: String?, password: String?, email: String?): BaseResult<Boolean> {
        delay(1000)
        return if (login == null || password == null || email == null) BaseResult(
            isSuccess = false, error = BaseFieldErrors.EMPTY
        )
        else userDataSource.register(User(login, password, email))
    }
}