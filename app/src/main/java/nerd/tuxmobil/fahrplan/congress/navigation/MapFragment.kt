package nerd.tuxmobil.fahrplan.congress.navigation

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import nerd.tuxmobil.fahrplan.congress.BuildConfig
import nerd.tuxmobil.fahrplan.congress.R

class MapFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_map, container, false)

    companion object {

        const val FRAGMENT_TAG = BuildConfig.APPLICATION_ID + ".MAP_FRAGMENT_TAG"

        @JvmStatic
        fun newInstance() = MapFragment()

    }

}
