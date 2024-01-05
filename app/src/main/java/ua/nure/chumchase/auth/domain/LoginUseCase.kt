package ua.nure.chumchase.auth.domain

import kotlinx.coroutines.delay
import ua.nure.chumchase.base.BaseResult

class LoginUseCase {
    suspend fun execute(login: String?, password: String?): BaseResult<Boolean> {
        delay(1000)
        return BaseResult(isSuccess = true, data = true)
    }
}