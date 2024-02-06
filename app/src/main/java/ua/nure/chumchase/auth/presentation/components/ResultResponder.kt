package ua.nure.chumchase.auth.presentation.components

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import ua.nure.chumchase.auth.domain.OperationStatusMessage
import ua.nure.chumchase.core.base.BaseViewModel

@Composable
fun ResultResponder(
    viewModel: BaseViewModel,
    onSuccess: () -> Unit,
    snackBarHostState: SnackbarHostState
) {
    val isSuccess by viewModel.isSuccess.observeAsState()
    if (isSuccess == true || isSuccess == false) {
        val context = LocalContext.current
        LaunchedEffect(isSuccess, snackBarHostState) {
            val message = viewModel.error.value ?: OperationStatusMessage.FAILURE.message
            if (isSuccess == true) onSuccess()
            else snackBarHostState.showSnackbar("G")
        }
    }
}