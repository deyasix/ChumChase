package ua.nure.chumchase.core.base

import ua.nure.chumchase.core.domain.ErrorMessage

data class BaseResult<T>(
    val isSuccess: Boolean,
    val error: ErrorMessage? = null,
    val data: T? = null
)