package nerd.tuxmobil.fahrplan.congress.commons

import androidx.annotation.PluralsRes
import androidx.annotation.StringRes

@Suppress("kotlin:S6517")
interface ResourceResolving {

    fun getString(
        @StringRes id: Int,
        vararg formatArgs: Any = emptyArray()
    ): String

    fun getQuantityString(
        @PluralsRes id: Int,
        quantity: Int,
        vararg formatArgs: Any = emptyArray()
    ): String

}
