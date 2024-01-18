package ua.nure.chumchase.auth.domain

import ua.nure.chumchase.auth.domain.model.RegisterUserDTO
import ua.nure.chumchase.core.base.BaseResult

class RegisterUseCase(private val userDataSource: UserDataSource) {
    suspend fun execute(login: String?, password: String?, email: String?): BaseResult<Boolean> {
        return if (login == null || password == null || email == null) BaseResult(
            isSuccess = false, error = BaseFieldErrors.EMPTY
        )
        else userDataSource.register(RegisterUserDTO(login, password, email))
    }
}