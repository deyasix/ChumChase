package ua.nure.chumchase.core.presentation.components

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import ua.nure.chumchase.core.base.BaseViewModel

@Composable
fun ResultResponder(
    viewModel: BaseViewModel,
    snackBarHostState: SnackbarHostState,
    onSuccess: () -> Unit = {},
) {
    val isSuccess by viewModel.isSuccess.observeAsState()
    val isLoading by viewModel.isLoading.observeAsState()
    val error by viewModel.error.observeAsState()
    val context = LocalContext.current
    if (isLoading == false) {
        LaunchedEffect(isSuccess, snackBarHostState) {
            if (isSuccess == true) onSuccess()
            error?.let {
                snackBarHostState.showSnackbar(context.getString(it))
            }
        }
    }
}