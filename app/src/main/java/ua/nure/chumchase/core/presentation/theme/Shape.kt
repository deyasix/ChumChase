package ua.nure.chumchase.core.presentation.theme

import androidx.compose.foundation.shape.*
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val shapes = Shapes(
    small = RoundedCornerShape(percent = 50),
    medium = RoundedCornerShape(0f),
    large = CutCornerShape(200.dp)
)