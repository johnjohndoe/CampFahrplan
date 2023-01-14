package nerd.tuxmobil.fahrplan.congress.sponsors

import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
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
        val toolbar = requireViewByIdCompat<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val actionBarColor = ContextCompat.getColor(this, R.color.colorActionBar)
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(actionBarColor))
        if (savedInstanceState == null) {
            addFragment(R.id.container, SponsorsFragment(), SponsorsFragment.FRAGMENT_TAG)
        }
    }

}
