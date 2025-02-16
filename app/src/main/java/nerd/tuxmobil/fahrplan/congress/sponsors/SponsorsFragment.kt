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
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import androidx.fragment.app.viewModels
import androidx.fragment.compose.content
import info.metadude.android.eventfahrplan.commons.flow.observe
import nerd.tuxmobil.fahrplan.congress.contract.BundleKeys
import nerd.tuxmobil.fahrplan.congress.extensions.replaceFragment
import nerd.tuxmobil.fahrplan.congress.extensions.withArguments
import nerd.tuxmobil.fahrplan.congress.sponsors.composables.SponsorsScreen
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

    private var sidePane = false
    private val viewModel: SponsorsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            sidePane = it.getBoolean(BundleKeys.SIDEPANE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = content {
        SponsorsScreen(
            sponsors = viewModel.sponsors,
            showInSidePane = sidePane,
            onViewEvent = viewModel::onViewEvent,
        )
    }.also { it.isClickable = true }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.navigateBack.observe(viewLifecycleOwner) {
            parentFragmentManager.popBackStack(FRAGMENT_TAG, POP_BACK_STACK_INCLUSIVE)
            if (!sidePane) {
                requireActivity().finish()
            }
        }
        viewModel.showSponsorWebsite.observe(viewLifecycleOwner) { sponsorUrl ->
            openSponsorUrl(sponsorUrl)
        }
    }

    private fun openSponsorUrl(sponsorUrl: String) {
        val intent = Intent(Intent.ACTION_VIEW, sponsorUrl.toUri())
        val context = requireContext()
        try {
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(context, "Error opening url '$sponsorUrl'.", Toast.LENGTH_LONG).show()
        }
    }

}
