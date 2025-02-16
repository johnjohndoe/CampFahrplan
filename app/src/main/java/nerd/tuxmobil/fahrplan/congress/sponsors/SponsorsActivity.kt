package nerd.tuxmobil.fahrplan.congress.sponsors

import android.content.Context
import android.content.Intent
import android.os.Bundle
import nerd.tuxmobil.fahrplan.congress.R
import nerd.tuxmobil.fahrplan.congress.base.BaseActivity

class SponsorsActivity : BaseActivity() {

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, SponsorsActivity::class.java)
            context.startActivity(intent)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sponsors)
        if (savedInstanceState == null) {
            addFragment(R.id.container, SponsorsFragment(), SponsorsFragment.FRAGMENT_TAG)
        }
    }

}
