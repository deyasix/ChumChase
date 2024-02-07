package ua.nure.chumchase.auth.presentation

import ua.nure.chumchase.core.domain.ErrorMessage

data class ValidState(val isValid: Boolean, val validMessage: ErrorMessage? = null)