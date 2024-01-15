package ua.nure.chumchase.core.profile.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import ua.nure.chumchase.core.profile.domain.model.Comment
import ua.nure.chumchase.R

@Composable
fun Comment(
    modifier: Modifier = Modifier,
    comment: Comment
) {
    Row(modifier) {
        ProfilePhoto(
            modifier = Modifier
                .size(dimensionResource(R.dimen.comment_profile_photo_size)),
            photoUrl = comment.photo
        )
        Column(
            Modifier.padding(start = dimensionResource(R.dimen.profile_vertical_padding)),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(comment.login, style = MaterialTheme.typography.bodyMedium)
            Text(comment.commentText, style = MaterialTheme.typography.bodySmall)
            Text(comment.dateTime, style = MaterialTheme.typography.labelSmall)
        }
    }
}