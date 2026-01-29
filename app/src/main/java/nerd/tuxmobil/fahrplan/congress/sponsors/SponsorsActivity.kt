package nerd.tuxmobil.fahrplan.congress.sponsors

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import nerd.tuxmobil.fahrplan.congress.base.BaseActivity
import nerd.tuxmobil.fahrplan.congress.designsystem.themes.EventFahrplanTheme
import nerd.tuxmobil.fahrplan.congress.sponsors.composables.SponsorsScreen

class SponsorsActivity : BaseActivity() {

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, SponsorsActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            EventFahrplanTheme {
                SponsorsScreen(
                    showInSidePane = false,
                    onBack = { finish() },
                )
            }
        }
    }
}
