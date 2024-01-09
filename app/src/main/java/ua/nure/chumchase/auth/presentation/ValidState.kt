package ua.nure.chumchase.auth.presentation

data class ValidState(val isValid: Boolean, val validMessage: ErrorMessage? = null)