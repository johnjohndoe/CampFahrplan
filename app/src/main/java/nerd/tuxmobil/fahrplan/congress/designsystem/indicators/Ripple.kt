package nerd.tuxmobil.fahrplan.congress.designsystem.indicators

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion
import androidx.compose.material3.ripple as Material3Ripple

@Stable
fun ripple(
    color: Color = Color.Unspecified,
) = Material3Ripple(
    color = color,
)
