package ua.nure.chumchase.core.base

import ua.nure.chumchase.core.domain.ErrorMessage

interface BaseResult {
    val isSuccess: Boolean
    val error: ErrorMessage?
}