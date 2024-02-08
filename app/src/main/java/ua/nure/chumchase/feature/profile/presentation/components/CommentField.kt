package ua.nure.chumchase.feature.profile.presentation.components

import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.unit.dp
import ua.nure.chumchase.R

@Composable
fun CommentField(
    modifier: Modifier = Modifier,
    initialText: String? = null,
    placeholder: String? = null,
    onChangeText: (String) -> Unit,
    onSendComment: () -> Unit,
    isCommentSending: Boolean? = false
) {
    val textStyle = MaterialTheme.typography.bodyMedium
    val color = MaterialTheme.colorScheme.secondary
    Row(
        modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        var currentComment by rememberSaveable { mutableStateOf(initialText ?: "") }
        OutlinedTextField(
            modifier = Modifier.weight(7f),
            value = currentComment,
            onValueChange = {
                currentComment = it
                onChangeText(it)
            },
            placeholder = {
                placeholder?.let {
                    Text(it, style = textStyle, color = color)
                }
            },
            textStyle = textStyle,
            singleLine = false,
            keyboardOptions = KeyboardOptions(
                autoCorrect = true, keyboardType = KeyboardType.Text
            ),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = color,
                focusedTextColor = color
            )
        )
        IconButton(onClick = {
            if (isCommentSending != true) {
                onSendComment()
                currentComment = ""
                onChangeText(currentComment)
            }
        }, modifier = Modifier.weight(1f)) {
            if (isCommentSending == true) CircularProgressIndicator(
                strokeWidth = 2.dp,
                modifier = Modifier.size(24.dp)
            )
            else Icon(
                Icons.Rounded.Send,
                stringResource(R.string.send_comment_description),
                tint = color
            )
        }
    }
}