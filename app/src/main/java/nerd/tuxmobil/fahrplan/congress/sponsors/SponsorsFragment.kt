package nerd.tuxmobil.fahrplan.congress.sponsors

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import nerd.tuxmobil.fahrplan.congress.contract.BundleKeys
import nerd.tuxmobil.fahrplan.congress.databinding.FragmentSponsorsBinding
import nerd.tuxmobil.fahrplan.congress.extensions.replaceFragment
import nerd.tuxmobil.fahrplan.congress.extensions.withArguments
import nerd.tuxmobil.fahrplan.congress.sponsors.factories.SponsorsViewFactory
import nerd.tuxmobil.fahrplan.congress.sponsors.models.Sponsor
import nerd.tuxmobil.fahrplan.congress.sponsors.viewmodels.SponsorsViewModel


class SponsorsFragment : Fragment() {

    companion object {

        const val FRAGMENT_TAG = "SponsorsFragment"

        fun replace(fragmentManager: FragmentManager, @IdRes containerViewId: Int, sidePane: Boolean) {
            val fragment = SponsorsFragment().withArguments(
                BundleKeys.SIDEPANE to sidePane
            )
            fragmentManager.replaceFragment(containerViewId, fragment, FRAGMENT_TAG, FRAGMENT_TAG)
        }

    }

    private var volatileBinding: FragmentSponsorsBinding? = null
    private val binding get() = volatileBinding!!
    private val viewModel: SponsorsViewModel by viewModels()
    private lateinit var sponsorsViewFactory: SponsorsViewFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        volatileBinding = FragmentSponsorsBinding.inflate(inflater, container, false)
        sponsorsViewFactory = SponsorsViewFactory(binding.root.context, ::openSponsorUrl)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val views = sponsorsViewFactory.composeViews(viewModel.loadSponsors())
        views.forEach(binding.sponsorsListView::addView)
    }

    override fun onDestroy() {
        volatileBinding = null
        super.onDestroy()
    }

    private fun openSponsorUrl(view: View) {
        val sponsor = view.tag as Sponsor
        val intent = Intent(Intent.ACTION_VIEW, sponsor.url.toUri())
        val context = requireContext()
        try {
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(context, "Error opening url '${sponsor.url}'.", Toast.LENGTH_LONG).show()
        }
    }

}
