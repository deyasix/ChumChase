package ua.nure.chumchase.base

data class BaseResult<T>(val isSuccess: Boolean, val error: String? = null, val data: T? = null)