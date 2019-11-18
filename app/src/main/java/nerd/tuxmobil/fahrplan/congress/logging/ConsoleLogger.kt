package nerd.tuxmobil.fahrplan.congress.logging

import nerd.tuxmobil.fahrplan.congress.MyApp
import org.ligi.tracedroid.logging.Log

object ConsoleLogger : Logging {

    override fun d(tag: String, message: String) {
        MyApp.LogDebug(tag, message)
    }

    override fun e(tag: String, message: String) {
        Log.e(tag, message)
    }

}
