package nerd.tuxmobil.fahrplan.congress.sponsors.composables

import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_VIEW
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import info.metadude.android.eventfahrplan.commons.flow.observe
import nerd.tuxmobil.fahrplan.congress.R
import nerd.tuxmobil.fahrplan.congress.extensions.showToast
import nerd.tuxmobil.fahrplan.congress.extensions.startActivity
import nerd.tuxmobil.fahrplan.congress.sponsors.SponsorsEffect.NavigateBack
import nerd.tuxmobil.fahrplan.congress.sponsors.SponsorsEffect.OpenSponsorUrl
import nerd.tuxmobil.fahrplan.congress.sponsors.viewmodels.SponsorsViewModel

@Composable
fun SponsorsScreen(
    viewModel: SponsorsViewModel = viewModel(),
    showInSidePane: Boolean,
    onBack: () -> Unit,
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(lifecycleOwner) {
        viewModel.effects.observe(lifecycleOwner) { effect ->
            when (effect) {
                NavigateBack -> onBack()
                is OpenSponsorUrl -> context.startActivity(effect.url)
            }
        }
    }

    SponsorsContent(
        sponsorByCategory = viewModel.sponsorByCategory,
        showInSidePane = showInSidePane,
        onViewEvent = viewModel::onViewEvent,
    )
}

private fun Context.startActivity(url: String) {
    val intent = Intent(ACTION_VIEW, url.toUri())
    startActivity(intent) {
        showToast(R.string.share_error_activity_not_found, showShort = true)
    }
}
