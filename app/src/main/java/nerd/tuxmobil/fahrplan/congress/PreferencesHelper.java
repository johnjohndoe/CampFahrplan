package nerd.tuxmobil.fahrplan.congress;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import info.metadude.android.typedpreferences.BooleanPreference;
import info.metadude.android.typedpreferences.LongPreference;
import info.metadude.android.typedpreferences.StringPreference;

public class PreferencesHelper {

    public static final String PREFS_ALARM_TIME_INDEX =
            "nerd.tuxmobil.fahrplan.congress.Prefs.ALARM_TIME_INDEX";

    public static final String PREFS_ALTERNATIVE_HIGHLIGHT =
            "nerd.tuxmobil.fahrplan.congress.Prefs.ALTERNATIVE_HIGHLIGHT";

    public static final String PREFS_CHANGES_SEEN =
            "nerd.tuxmobil.fahrplan.congress.Prefs.CHANGES_SEEN";

    public static final String PREFS_SCHEDULE_URL =
            "nerd.tuxmobil.fahrplan.congress.Prefs.SCHEDULE_URL";

    protected final IntPreference alarmTimeIndexPreference;

    protected final BooleanPreference alternativeHighlightPreference;

    protected final BooleanPreference autoUpdatePreference;

    protected final BooleanPreference changesSeenPreference;

    protected final BooleanPreference insistentAlarmPreference;

    protected final LongPreference lastFetchPreferences;

    protected final StringPreference reminderTonePreference;

    protected final StringPreference scheduleUrlPreference;

    public PreferencesHelper(@NonNull SharedPreferences sharedPreferences,
                             @NonNull Context context) {
        int defaultAlarmTimeIndex = context.getResources().getInteger(R.integer.default_alarm_time_index);
        alarmTimeIndexPreference = new IntPreference(
                sharedPreferences, PREFS_ALARM_TIME_INDEX, defaultAlarmTimeIndex);
        alternativeHighlightPreference = new BooleanPreference(
                sharedPreferences, PREFS_ALTERNATIVE_HIGHLIGHT, false);
        autoUpdatePreference = new BooleanPreference(
                sharedPreferences, "auto_update", false);
        changesSeenPreference = new BooleanPreference(
                sharedPreferences, PREFS_CHANGES_SEEN, true);
        insistentAlarmPreference = new BooleanPreference(
                sharedPreferences, "insistent", false);
        lastFetchPreferences = new LongPreference(
                sharedPreferences, "last_fetch", 0);
        reminderTonePreference = new StringPreference(
                sharedPreferences, "reminder_tone", "");
        scheduleUrlPreference = new StringPreference(
                sharedPreferences, PREFS_SCHEDULE_URL, null);
    }

}
