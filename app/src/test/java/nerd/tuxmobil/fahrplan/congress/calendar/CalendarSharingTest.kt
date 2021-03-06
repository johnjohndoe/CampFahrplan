package nerd.tuxmobil.fahrplan.congress.calendar

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import com.nhaarman.mockitokotlin2.argThat
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import nerd.tuxmobil.fahrplan.congress.extensions.startActivity
import nerd.tuxmobil.fahrplan.congress.models.Session
import org.junit.After
import org.junit.Test
import org.mockito.ArgumentMatcher
import org.mockito.Mockito.validateMockitoUsage

/**
 * Covers [CalendarSharing.addToCalendar].
 * Does not cover Android framework routines such [Context.startActivity].
 */
class CalendarSharingTest {

    private val context = mock<Context>()
    private val onFailure: () -> Unit = mock()

    @Test
    fun `addToCalendar composes and emits a calendar insert intent`() {
        CalendarSharing(context, createSession(), FakeComposer, onFailure).addToCalendar()
        verify(context).startActivity(argThat(InsertIntentMatcher()))
        verify(onFailure, never()).invoke()
    }

    @After
    fun validate() {
        validateMockitoUsage()
    }

    private class InsertIntentMatcher : ArgumentMatcher<Intent> {

        override fun matches(intent: Intent) = intent.action == Intent.ACTION_INSERT &&
                intent.toUri(0) == "#Intent;action=android.intent.action.INSERT;" +
                "S.description=Lorem%20ipsum%20dolor;l.beginTime=1439478900000;l.endTime=1439480700000;" +
                "S.title=Title;S.eventLocation=Room;end" &&
                intent.extras != null && matchesExtras(intent.extras!!)

        private fun matchesExtras(extras: Bundle) =
            extras.get(CalendarContract.Events.TITLE) == "Title" &&
                    extras.get(CalendarContract.Events.DESCRIPTION) == "Lorem ipsum dolor" &&
                    extras.get(CalendarContract.Events.EVENT_LOCATION) == "Room" &&
                    extras.get(CalendarContract.EXTRA_EVENT_BEGIN_TIME) == 1439478900000L &&
                    extras.get(CalendarContract.EXTRA_EVENT_END_TIME) == 1439480700000L

        override fun toString() = "Session intent does not match."
    }

    private object FakeComposer : CalendarDescriptionComposition {
        override fun getCalendarDescription() = "Lorem ipsum dolor"
    }

    private fun createSession() = Session("2342").apply {
        title = "Title"
        subtitle = "Subtitle"
        speakers = "Speakers"
        abstractt = "Abstract"
        description = "Description"
        links = "Links"
        room = "Room"
        dateUTC = 1439478900000L
        duration = 30
    }

}
