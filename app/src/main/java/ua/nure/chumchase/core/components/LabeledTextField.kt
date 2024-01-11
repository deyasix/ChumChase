package ua.nure.chumchase.core.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import ua.nure.chumchase.auth.domain.BaseFieldErrors
import ua.nure.chumchase.auth.presentation.ValidState

@Composable
fun LabeledTextField(
    modifier: Modifier = Modifier,
    label: String? = null,
    initialText: String? = null,
    onChangeText: (String) -> Unit,
    isValidInput: (String) -> ValidState = ::checkForEmptiness,
    isPassword: Boolean = false,
    isEmail: Boolean = false
) {
    Column(modifier) {
        var text by rememberSaveable { mutableStateOf(initialText ?: "") }

        var validState by remember {
            mutableStateOf(
                if (initialText != null) isValidInput(initialText)
                else ValidState(true)
            )
        }

        TextField(
            value = text,
            onValueChange = {
                text = it
                onChangeText(it)
                validState = isValidInput(it)
            },
            textStyle = MaterialTheme.typography.bodyMedium,
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            label = {
                label?.let {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            },
            singleLine = true,
            isError = !validState.isValid,
            keyboardOptions = KeyboardOptions(
                autoCorrect = true,
                keyboardType = if (isEmail) KeyboardType.Email else if (isPassword) KeyboardType.Password else KeyboardType.Text
            ),
            supportingText = {
                validState.validMessage?.let {
                    Text(stringResource(it.message), style = MaterialTheme.typography.bodySmall)
                }
            }
        )
    }
}

private fun checkForEmptiness(text: String): ValidState {
    val isValid = text.isNotBlank()
    return if (isValid) ValidState(true)
    else ValidState(false, BaseFieldErrors.EMPTY)
}