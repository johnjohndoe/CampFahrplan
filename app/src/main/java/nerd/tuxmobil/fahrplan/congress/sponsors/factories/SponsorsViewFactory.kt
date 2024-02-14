package nerd.tuxmobil.fahrplan.congress.sponsors.factories

import android.content.Context
import android.os.Build
import android.view.Gravity
import android.view.Gravity.CENTER
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import androidx.core.widget.TextViewCompat.setTextAppearance
import nerd.tuxmobil.fahrplan.congress.R
import nerd.tuxmobil.fahrplan.congress.sponsors.models.Category
import nerd.tuxmobil.fahrplan.congress.sponsors.models.Sponsor

class SponsorsViewFactory(

    val context: Context,
    private val onSponsorItemClick: (view: View) -> Unit

) {

    companion object {
        private const val CATEGORY_PADDING = 16
        private const val IMAGE_WIDTH = 600
        private const val IMAGE_HEIGHT = 450
    }

    private val categoryColor = ContextCompat.getColor(context, R.color.text_link_on_light)

    /**
     * Returns a grouped list of category and sponsor views.
     */
    fun composeViews(sponsors: List<Sponsor>): List<View> {
        val views = mutableListOf<View>()
        sponsors
            .groupBy { it.category }
            .forEach { sponsorsByCategory ->
                views.add(sponsorsByCategory.key.toView())
                sponsorsByCategory.value.forEach { sponsor ->
                    views.add(sponsor.toViewLayout().apply { addView(sponsor.toView()) })
                }
            }
        return views
    }

    private fun Category.toView() = TextView(context).apply {
        setPadding(CATEGORY_PADDING)
        text = context.getString(title)
        setTextAppearance(this, R.style.TextAppearance_AppCompat_Medium_Inverse)
        setTextColor(categoryColor)
    }

    private fun Sponsor.toViewLayout() = LinearLayout(context).apply {
        layoutParams = LayoutParams(MATCH_PARENT, IMAGE_HEIGHT)
        gravity = CENTER
        tag = this@toViewLayout
        setOnClickListener(onSponsorItemClick)
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            foreground = ContextCompat.getDrawable(context, R.drawable.sponsor_item_background)
        }
    }

    private fun Sponsor.toView() = ImageView(context).apply {
        layoutParams = LayoutParams(IMAGE_WIDTH, IMAGE_HEIGHT)
        setImageResource(imageUrl)
        contentDescription = description
    }

}
