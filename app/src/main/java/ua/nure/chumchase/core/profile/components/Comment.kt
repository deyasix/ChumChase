package ua.nure.chumchase.core.profile.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.nure.chumchase.core.profile.domain.model.Comment

@Composable
fun Comment(
    modifier: Modifier = Modifier,
    comment: Comment
) {
    Row(modifier) {
        ProfilePhoto(
            modifier = Modifier
                .size(40.dp), photoUrl = comment.photo
        )
        Column(Modifier.padding(start = 8.dp), verticalArrangement = Arrangement.SpaceAround) {
            Text(comment.login, style = MaterialTheme.typography.bodyMedium)
            Text(comment.commentText, style = MaterialTheme.typography.bodySmall)
            Text(comment.dateTime, style = MaterialTheme.typography.labelSmall)
        }
    }
}