package ua.nure.chumchase.core.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.NoPhotography
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.glide.GlideImage
import ua.nure.chumchase.R

@Composable
fun ProfilePhoto(modifier: Modifier = Modifier, photoUrl: String?) {
    val photoModifier = modifier
        .clip(CircleShape)
        .size(80.dp, 80.dp)
        .border(
            dimensionResource(R.dimen.profile_photo_border),
            MaterialTheme.colorScheme.primary,
            CircleShape
        )
    val description = stringResource(R.string.profile_photo_description)
    val absentImage = rememberVectorPainter(Icons.Rounded.NoPhotography)
    if (photoUrl == null) {
        Image(absentImage, description, photoModifier)
    } else GlideImage(imageModel = { photoUrl }, modifier = photoModifier)
}