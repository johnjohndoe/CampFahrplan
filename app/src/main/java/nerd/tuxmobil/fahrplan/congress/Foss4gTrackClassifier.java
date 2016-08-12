package nerd.tuxmobil.fahrplan.congress;

import android.support.annotation.NonNull;
import android.text.TextUtils;

public class Foss4gTrackClassifier {

    public static void customizeTrack(@NonNull Lecture lecture) {
        String title = lecture.title;
        if (TextUtils.isEmpty(title)) {
            return;
        }
        String lowerCaseTitle = title.toLowerCase();
        if (lowerCaseTitle.contains("bof session")) {
            lecture.track = "BoF Session";
        } else if (lowerCaseTitle.contains("introduction")) {
            lecture.track = "Introduction";
        } else if (lowerCaseTitle.contains("closing")) {
            lecture.track = "Introduction";
        } else if (lowerCaseTitle.contains("keynote")) {
            lecture.track = "Keynote";
        } else if (lowerCaseTitle.contains("lightning talks")) {
            lecture.track = "Lightning Talks";
        } else if (lowerCaseTitle.contains("topic talk")) {
            lecture.track = "Topic Talk";
        }
    }

}
