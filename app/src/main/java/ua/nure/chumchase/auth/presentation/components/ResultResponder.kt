package ua.nure.chumchase.auth.presentation.components

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import timber.log.Timber
import ua.nure.chumchase.core.domain.OperationStatusMessage
import ua.nure.chumchase.core.base.BaseViewModel

@Composable
fun ResultResponder(
    viewModel: BaseViewModel,
    onSuccess: () -> Unit,
    snackBarHostState: SnackbarHostState
) {
    val isSuccess by viewModel.isSuccess.observeAsState()
    val isLoading by viewModel.isLoading.observeAsState()
    val error by viewModel.error.observeAsState()
    val context = LocalContext.current
    if (isLoading == false) {
        LaunchedEffect(isSuccess, snackBarHostState) {
            val message = viewModel.error.value ?: OperationStatusMessage.FAILURE.message
            Timber.d(message.toString())
            if (isSuccess == true) onSuccess()
            error?.let {
                snackBarHostState.showSnackbar(context.getString(it))
            }
        }
    }
}