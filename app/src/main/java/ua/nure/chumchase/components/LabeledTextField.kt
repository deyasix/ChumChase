package ua.nure.chumchase.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun LabeledTextField(
    modifier: Modifier = Modifier,
    label: String? = null,
    initialText: String? = null,
    onChangeText: (String) -> Unit,
    isPassword: Boolean = false
) {
    Column(modifier) {
        var text by rememberSaveable { mutableStateOf(initialText ?: "") }

        TextField(
            value = text,
            onValueChange = {
                text = it
                onChangeText(it)
            },
            textStyle = MaterialTheme.typography.bodyMedium,
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            label = {
                label?.let {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        )
    }

}