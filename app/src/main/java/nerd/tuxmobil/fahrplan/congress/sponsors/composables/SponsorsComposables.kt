package nerd.tuxmobil.fahrplan.congress.sponsors.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import nerd.tuxmobil.fahrplan.congress.R
import nerd.tuxmobil.fahrplan.congress.commons.EventFahrplanTheme
import nerd.tuxmobil.fahrplan.congress.commons.MultiDevicePreview
import nerd.tuxmobil.fahrplan.congress.commons.TopBar
import nerd.tuxmobil.fahrplan.congress.extensions.toTextUnit
import nerd.tuxmobil.fahrplan.congress.sponsors.SponsorsViewEvent
import nerd.tuxmobil.fahrplan.congress.sponsors.SponsorsViewEvent.OnBackClick
import nerd.tuxmobil.fahrplan.congress.sponsors.SponsorsViewEvent.OnSponsorUrlClick
import nerd.tuxmobil.fahrplan.congress.sponsors.factories.SponsorsFactory
import nerd.tuxmobil.fahrplan.congress.sponsors.models.Category
import nerd.tuxmobil.fahrplan.congress.sponsors.models.Sponsor

@Composable
internal fun SponsorsScreen(
    sponsors: List<Sponsor>,
    showInSidePane: Boolean,
    onViewEvent: (SponsorsViewEvent) -> Unit,
) {
    EventFahrplanTheme {
        Scaffold(
            topBar = {
                if (!showInSidePane) {
                    TopBar(
                        title = stringResource(R.string.sponsors_screen_name),
                        onBack = { onViewEvent(OnBackClick) },
                    )
                }
            },
            content = { contentPadding ->
                Box(
                    Modifier
                        .background(Color.White) // TODO hardcoded until dark theme is ready
                        .padding(contentPadding)
                        .fillMaxSize() // Prevent background flickering on load
                        .verticalScroll(rememberScrollState())
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = dimensionResource(R.dimen.list_pane_leftright_padding)),
                        horizontalAlignment = CenterHorizontally,
                    ) {
                        sponsors.groupBy { it.category }
                            .forEach { (category, sponsors) ->
                                CategoryItem(category)
                                sponsors.forEach { sponsor ->
                                    SponsorItem(
                                        sponsor = sponsor,
                                        onClick = { url -> onViewEvent(OnSponsorUrlClick(url)) },
                                    )
                                }
                            }
                    }
                }
            }
        )
    }
}

@Composable
private fun CategoryItem(category: Category) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        text = stringResource(category.title),
        textAlign = TextAlign.Start,
        fontSize = dimensionResource(R.dimen.sponsors_category_item_text).toTextUnit(),
        color = colorResource(R.color.sponsors_category_item_text),
    )
}

@Composable
private fun SponsorItem(sponsor: Sponsor, onClick: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clip(RoundedCornerShape(dimensionResource(R.dimen.sponsors_sponsor_item_corner_radius)))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(color = colorResource(R.color.sponsors_item_ripple_background)),
                onClick = { onClick(sponsor.url) },
            ),
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(R.dimen.sponsors_sponsor_image_height))
                .width(dimensionResource(R.dimen.sponsors_sponsor_image_width))
                .padding(16.dp),
            painter = painterResource(sponsor.imageUrl),
            contentDescription = sponsor.description,
        )

    }
}

@MultiDevicePreview
@Composable
private fun SponsorsScreenPreview() {
    SponsorsScreen(
        sponsors = SponsorsFactory.createSponsors(),
        showInSidePane = false,
        onViewEvent = {},
    )
}
