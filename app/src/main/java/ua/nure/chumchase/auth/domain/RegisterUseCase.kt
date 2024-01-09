package ua.nure.chumchase.auth.domain

import kotlinx.coroutines.delay
import ua.nure.chumchase.base.BaseResult

class RegisterUseCase {
    suspend fun execute(login: String?, password: String?, email: String?): BaseResult<Boolean> {
        delay(1000)
        return BaseResult(isSuccess = true, data = true)
    }
}