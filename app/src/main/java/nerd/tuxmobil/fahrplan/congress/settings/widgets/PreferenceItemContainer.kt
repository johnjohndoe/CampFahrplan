package nerd.tuxmobil.fahrplan.congress.settings.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import nerd.tuxmobil.fahrplan.congress.designsystem.dividers.DividerHorizontal

@Composable
internal fun PreferenceItemContainer(
    modifier: Modifier = Modifier,
    showDivider: Boolean = false,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        content()
        if (showDivider) {
            DividerHorizontal()
        }
    }
}

