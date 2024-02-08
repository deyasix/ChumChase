package ua.nure.chumchase.core.base

import ua.nure.chumchase.core.domain.ErrorMessage

data class BaseDataResult<T>(
    override val isSuccess: Boolean, override val error: ErrorMessage? = null, val data: T? = null
) : BaseResult