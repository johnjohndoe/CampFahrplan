package nerd.tuxmobil.fahrplan.congress;

import android.support.annotation.NonNull;
import android.support.v4.util.SparseArrayCompat;
import android.util.Log;

public class Foss4gEventUrlGenerator {

    private static final int ACADEMIC_TRACK_MAX_ID = 1000;

    private static final int GENERAL_TRACK_MAX_ID = 1500;

    private static final String FOSS4G_2016_WEB_BASE_URL = "http://foss4g2016.org/talks.html#";

    private static final SparseArrayCompat FRAB_ID_TO_WEB_URL_MAPPING =
            new SparseArrayCompat<String>() {{

                // Day 1, 24.08.2016
                put(1562, ""); // Introduction (Till)
                put(1563, ""); // Introduction (Mayor of Bonn)
                put(1564, ""); // Lightning Talks I
                put(1565, "http://2016.foss4g.org/programme.html#andreas-veispak");
                put(1566, "http://2016.foss4g.org/programme.html#dirk-frigne");
                put(1567, ""); // BoF Session I
                put(5125, ""); // BoF Session
                put(5126, ""); // BoF Session
                put(5127, ""); // BoF Session
                put(5128, ""); // BoF Session
                put(5129, ""); // BoF Session
                put(5130, ""); // BoF Session
                put(5131, ""); // BoF Session
                put(5118, "http://2016.foss4g.org/programme.html#topic-talk-1");
                put(5119, "http://2016.foss4g.org/programme.html#topic-talk-2");
                put(5120, "http://2016.foss4g.org/programme.html#topic-talk-3");

                // Day 2, 25.08.2016
                put(1568, ""); // Introduction (Till) Day 2
                put(1569, "http://2016.foss4g.org/programme.html#thomas-zerweck"); // Keynote III
                put(1570, "http://2016.foss4g.org/programme.html#bianca-hoersch"); // Keynote IV
                put(1571, ""); // OSGeo AGM
                put(5132, ""); // BoF Session
                put(5133, ""); // BoF Session
                put(5134, ""); // BoF Session
                put(5135, ""); // BoF Session
                put(5136, ""); // BoF Session
                put(5137, ""); // BoF Session
                put(5138, ""); // BoF Session
                put(5122, "http://2016.foss4g.org/programme.html#topic-talk-5");
                put(5121, "http://2016.foss4g.org/programme.html#topic-talk-4");
                put(5123, "http://2016.foss4g.org/programme.html#topic-talk-6");

                // Day 3, 26.08.2016
                put(1572, ""); // Introduction (Till) Day 3
                put(1573, "http://2016.foss4g.org/programme.html#ton-zijlstra"); // Keynote V
                put(1574, "http://2016.foss4g.org/programme.html#klaus-deininger"); // Keynote VI
                put(1575, "http://2016.foss4g.org/programme.html#peter-kusterer"); // Keynote VII
                put(1576, ""); // Sol Katz Award & Students Award
                put(1577, ""); // Closing
                put(5124, "http://2016.foss4g.org/programme.html#topic-talk-8");
            }};

    @NonNull
    public static String getEventUrl(@NonNull String eventIdentifier) {
        int frabEventId = Integer.parseInt(eventIdentifier);
        String webUrl = (String) FRAB_ID_TO_WEB_URL_MAPPING.get(frabEventId);
        if (webUrl != null) {
            // Might return an empty string
            return webUrl;
        }
        int webEventId = getWebEventId(frabEventId);
        String url = getUrl(webEventId);
        Log.d(FahrplanMisc.class.getName(), "URLs: OLD = " + eventIdentifier + ", NEW = " + url);
        return url;
    }

    private static int getWebEventId(int frabEventId) {
        if (frabEventId < GENERAL_TRACK_MAX_ID) {
            return frabEventId - ACADEMIC_TRACK_MAX_ID;
        } else {
            return frabEventId - GENERAL_TRACK_MAX_ID;
        }
    }

    @NonNull
    private static String getUrl(int webEventId) {
        String digitsPrefix = "";
        if (webEventId < 100) {
            digitsPrefix = "0";
        }
        return FOSS4G_2016_WEB_BASE_URL + digitsPrefix + webEventId;
    }

}
