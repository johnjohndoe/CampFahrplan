package nerd.tuxmobil.fahrplan.congress.designsystem.bars

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import nerd.tuxmobil.fahrplan.congress.designsystem.themes.EventFahrplanTheme

/**
 * Gradient protection drawn behind the system status bar so that
 * scrolling content stays legible, as recommended by the edge-to-edge
 * guidance, see https://developer.android.com/develop/ui/compose/system/system-bars#system-bar-protection
 *
 * Overlay this composable at the top of a full screen container which
 * content is drawn edge-to-edge, e.g. in a [androidx.compose.foundation.layout.Box],
 * after drawing the main content.
 */
@Composable
fun StatusBarProtection(
    modifier: Modifier = Modifier,
    color: Color = EventFahrplanTheme.colorScheme.background,
) {
    Spacer(
        modifier = modifier
            .fillMaxWidth()
            .height(
                with(LocalDensity.current) {
                    (WindowInsets.statusBars.getTop(this) * 1.2f).toDp()
                }
            )
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        color.copy(alpha = 1f),
                        color.copy(alpha = 0.8f),
                        Color.Transparent,
                    )
                )
            ),
    )
}

