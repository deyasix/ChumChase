package ua.nure.chumchase.core.utils

import retrofit2.Response
import ua.nure.chumchase.core.base.BaseDataResult
import ua.nure.chumchase.core.base.BaseOperationResult
import ua.nure.chumchase.core.domain.AuthResponseMessage
import ua.nure.chumchase.core.domain.DomainMapper
import ua.nure.chumchase.core.domain.ErrorMessage
import ua.nure.chumchase.core.domain.OperationStatusMessage
import java.net.SocketTimeoutException

fun <T> Response<T>.handle(
    onSuccess: (T?) -> Unit = {}
): BaseOperationResult {
    var isSuccess = false
    var error: ErrorMessage? = null
    if (isSuccessful) {
        onSuccess(body())
        isSuccess = true
    } else {
        error =
            AuthResponseMessage.getResponseMessage(errorBody()?.getErrorMessage())
    }
    return BaseOperationResult(isSuccess, error)
}

fun <T, V> Response<V>.handleData(
    domainMapper: DomainMapper<V, T>,
    onSuccess: (V?) -> Unit = {}
): BaseDataResult<T> {
    var isSuccess = false
    var error: ErrorMessage?
    var data: V? = null
    try {
        data = body()
        if (isSuccessful && data != null) {
            onSuccess(data)
            isSuccess = true
            error = null
        } else {
            error =
                AuthResponseMessage.getResponseMessage(errorBody()?.getErrorMessage())
        }
    } catch (exception: SocketTimeoutException) {
        error = OperationStatusMessage.TIMEOUT
    }
    val result = data?.let { domainMapper.mapToDomainModel(it) }
    return BaseDataResult(isSuccess, error, result)
}