package ua.nure.chumchase.core.base

import ua.nure.chumchase.core.domain.ErrorMessage

data class BaseOperationResult(
    override val isSuccess: Boolean,
    override val error: ErrorMessage? = null
) : BaseResult