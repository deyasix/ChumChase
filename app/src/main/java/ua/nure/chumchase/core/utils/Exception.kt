package ua.nure.chumchase.core.utils

import ua.nure.chumchase.core.domain.ErrorMessage
import ua.nure.chumchase.core.domain.OperationStatusMessage
import java.net.SocketTimeoutException

fun Exception.getErrorMessage(): ErrorMessage {
    return when(this) {
        is SocketTimeoutException -> OperationStatusMessage.TIMEOUT
        else -> OperationStatusMessage.FAILURE
    }
}