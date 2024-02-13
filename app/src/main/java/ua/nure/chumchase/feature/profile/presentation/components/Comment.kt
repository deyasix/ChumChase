package ua.nure.chumchase.feature.profile.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import ua.nure.chumchase.R

@Composable
fun Comment(
    modifier: Modifier = Modifier,
    photoUrl: String?,
    login: String,
    text: String,
    date: String
) {
    Row(modifier) {
        ProfilePhoto(
            modifier = Modifier
                .size(dimensionResource(R.dimen.comment_profile_photo_size))
                .clickable { },
            photoUrl = photoUrl
        )
        Column(
            Modifier.padding(start = dimensionResource(R.dimen.profile_vertical_padding)),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(login, style = MaterialTheme.typography.bodyMedium)
            Text(text, style = MaterialTheme.typography.bodySmall)
            Text(date, style = MaterialTheme.typography.labelSmall)
        }
    }
}