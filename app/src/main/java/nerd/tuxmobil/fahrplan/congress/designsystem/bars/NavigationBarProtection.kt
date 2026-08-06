package nerd.tuxmobil.fahrplan.congress.designsystem.bars

import android.provider.Settings
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import nerd.tuxmobil.fahrplan.congress.designsystem.themes.EventFahrplanTheme

/**
 * Gradient protection drawn behind the system navigation bar so that
 * scrolling content stays legible, as recommended by the edge-to-edge
 * guidance, see https://developer.android.com/develop/ui/compose/system/system-bars#system-bar-protection
 *
 * Overlay this composable at the bottom of a full screen container which
 * content is drawn edge-to-edge, e.g. in a [androidx.compose.foundation.layout.Box],
 * after drawing the main content.
 */
@Composable
fun NavigationBarProtection(
    modifier: Modifier = Modifier,
    color: Color = EventFahrplanTheme.colorScheme.background,
) {
    if (!isGestureNavigationActive()) return

    Spacer(
        modifier = modifier
            .fillMaxWidth()
            .height(
                with(LocalDensity.current) {
                    (WindowInsets.navigationBars.getBottom(this) * 1.2f).toDp()
                }
            )
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Transparent,
                        color.copy(alpha = 0.8f),
                        color.copy(alpha = 1f),
                    )
                )
            ),
    )
}

@Composable
private fun isGestureNavigationActive(): Boolean {
    val context = LocalContext.current
    return remember(context) {
        Settings.Secure.getInt(
            context.contentResolver,
            NAVIGATION_MODE_KEY,
            NAVIGATION_MODE_FULLY_GESTURAL,
        ) == NAVIGATION_MODE_FULLY_GESTURAL
    }
}

/**
 * See value of `NAVIGATION_MODE` in [Settings.Secure].
 */
private const val NAVIGATION_MODE_KEY = "navigation_mode"

/**
 * See values documented by `NAVIGATION_MODE` in [Settings.Secure].
 */
private const val NAVIGATION_MODE_FULLY_GESTURAL = 2
