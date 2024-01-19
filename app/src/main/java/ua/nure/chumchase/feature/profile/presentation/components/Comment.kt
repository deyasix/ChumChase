package ua.nure.chumchase.feature.profile.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import ua.nure.chumchase.feature.profile.domain.model.CommentDTO
import ua.nure.chumchase.R

@Composable
fun Comment(
    modifier: Modifier = Modifier,
    commentDTO: CommentDTO
) {
    Row(modifier) {
        ProfilePhoto(
            modifier = Modifier
                .size(dimensionResource(R.dimen.comment_profile_photo_size)).clickable {  },
            photoUrl = commentDTO.authorPhotoUrl
        )
        Column(
            Modifier.padding(start = dimensionResource(R.dimen.profile_vertical_padding)),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(commentDTO.login, style = MaterialTheme.typography.bodyMedium)
            Text(commentDTO.text, style = MaterialTheme.typography.bodySmall)
            Text(commentDTO.dateTime, style = MaterialTheme.typography.labelSmall)
        }
    }
}