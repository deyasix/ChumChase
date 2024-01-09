package ua.nure.chumchase.base

import ua.nure.chumchase.auth.domain.ErrorMessage

data class BaseResult<T>(
    val isSuccess: Boolean,
    val error: ErrorMessage? = null,
    val data: T? = null
)