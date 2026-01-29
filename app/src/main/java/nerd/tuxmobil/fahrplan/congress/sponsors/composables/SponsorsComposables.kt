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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import nerd.tuxmobil.fahrplan.congress.R
import nerd.tuxmobil.fahrplan.congress.commons.MultiDevicePreview
import nerd.tuxmobil.fahrplan.congress.designsystem.bars.TopBar
import nerd.tuxmobil.fahrplan.congress.designsystem.indicators.ripple
import nerd.tuxmobil.fahrplan.congress.designsystem.templates.Scaffold
import nerd.tuxmobil.fahrplan.congress.designsystem.texts.Text
import nerd.tuxmobil.fahrplan.congress.designsystem.themes.EventFahrplanTheme
import nerd.tuxmobil.fahrplan.congress.extensions.toTextUnit
import nerd.tuxmobil.fahrplan.congress.sponsors.SponsorsViewEvent
import nerd.tuxmobil.fahrplan.congress.sponsors.SponsorsViewEvent.OnBackClick
import nerd.tuxmobil.fahrplan.congress.sponsors.SponsorsViewEvent.OnSponsorUrlClick
import nerd.tuxmobil.fahrplan.congress.sponsors.factories.SponsorsFactory
import nerd.tuxmobil.fahrplan.congress.sponsors.models.Category
import nerd.tuxmobil.fahrplan.congress.sponsors.models.Sponsor

@Composable
fun SponsorsContent(
    sponsorByCategory: List<Pair<Category, Sponsor>>,
    showInSidePane: Boolean,
    onViewEvent: (SponsorsViewEvent) -> Unit,
) {
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
                    sponsorByCategory
                        .groupBy { it.first }
                        .forEach { (category, categorySponsors) ->
                            CategoryHeadline(category)
                            categorySponsors.forEach { sponsorByCategory ->
                                SponsorCard(
                                    sponsor = sponsorByCategory.second,
                                    onClick = { onViewEvent(OnSponsorUrlClick(it)) },
                                )
                            }
                        }
                }
            }
        },
    )
}

@Composable
private fun CategoryHeadline(category: Category) {
    Text(
        modifier = Modifier
            .width(dimensionResource(R.dimen.sponsors_sponsor_image_width))
            .padding(vertical = 16.dp),
        text = stringResource(category.title),
        textAlign = TextAlign.Start,
        fontSize = dimensionResource(R.dimen.sponsors_category_item_text).toTextUnit(),
        color = colorResource(R.color.sponsors_category_item_text),
    )
}

@Composable
private fun SponsorCard(sponsor: Sponsor, onClick: (String) -> Unit) {
    Box(
        modifier = Modifier
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
                .background(White)
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
private fun SponsorsContentPreview() {
    EventFahrplanTheme {
        SponsorsContent(
            sponsorByCategory = SponsorsFactory.create(),
            showInSidePane = false,
            onViewEvent = { },
        )
    }
}
