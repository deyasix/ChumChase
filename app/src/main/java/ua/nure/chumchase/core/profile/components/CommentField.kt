package ua.nure.chumchase.core.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import ua.nure.chumchase.R

@Composable
fun CommentField(
    modifier: Modifier = Modifier,
    initialText: String? = null,
    placeholder: String? = null,
    onChangeText: (String) -> Unit,
    onSendComment: () -> Unit
) {
    val textStyle = MaterialTheme.typography.bodyMedium
    Row(
        modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        var currentComment by rememberSaveable { mutableStateOf(initialText ?: "") }
        OutlinedTextField(
            modifier = Modifier.weight(1f),
            value = currentComment,
            onValueChange = {
                currentComment = it
                onChangeText(it)
            },
            placeholder = {
                placeholder?.let {
                    Text(it, style = textStyle)
                }
            },
            textStyle = textStyle,
            singleLine = false,
            keyboardOptions = KeyboardOptions(
                autoCorrect = true, keyboardType = KeyboardType.Text
            )
        )
        IconButton(onClick = {
            onSendComment()
            currentComment = ""
            onChangeText(currentComment)
        }) {
            Icon(Icons.Rounded.Send, stringResource(R.string.send_comment_description))
        }
    }
}