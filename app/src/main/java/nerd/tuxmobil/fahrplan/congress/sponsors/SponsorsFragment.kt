package nerd.tuxmobil.fahrplan.congress.sponsors

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import androidx.fragment.compose.content
import nerd.tuxmobil.fahrplan.congress.contract.BundleKeys
import nerd.tuxmobil.fahrplan.congress.designsystem.themes.EventFahrplanTheme
import nerd.tuxmobil.fahrplan.congress.extensions.replaceFragment
import nerd.tuxmobil.fahrplan.congress.extensions.withArguments
import nerd.tuxmobil.fahrplan.congress.sponsors.composables.SponsorsScreen

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
        EventFahrplanTheme {
            SponsorsScreen(
                showInSidePane = sidePane,
                onBack = {
                    parentFragmentManager.popBackStack(FRAGMENT_TAG, POP_BACK_STACK_INCLUSIVE)
                    if (!sidePane) {
                        requireActivity().finish()
                    }
                },
            )
        }
    }.also { it.isClickable = true }
}
