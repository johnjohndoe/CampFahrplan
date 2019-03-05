package nerd.tuxmobil.fahrplan.congress.navigation

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.content.ContextCompat
import nerd.tuxmobil.fahrplan.congress.R
import nerd.tuxmobil.fahrplan.congress.base.BaseActivity

class MapActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            val color = ContextCompat.getColor(this, R.color.colorActionBar)
            it.setBackgroundDrawable(ColorDrawable(color))
        }
    }

}
