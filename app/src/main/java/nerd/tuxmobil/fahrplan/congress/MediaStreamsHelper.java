package nerd.tuxmobil.fahrplan.congress;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import info.metadude.java.library.brockman.models.Offer;
import info.metadude.java.library.brockman.models.Room;
import info.metadude.java.library.brockman.models.Stream;
import info.metadude.java.library.brockman.models.Url;

public abstract class MediaStreamsHelper {

    public static
    @NonNull
    List<Stream> getStreamsForLectureRoom(
            @NonNull List<Offer> offers, @NonNull String lectureRoom) {
        List<Stream> filteredStreams = new ArrayList<Stream>(8);
        for (Offer offer : offers) {
            for (Room room : offer.rooms) {
                String roomName = room.schedulename;
                // TODO Remove this hack when production URL has been published
                if (BuildConfig.DEBUG && BuildConfig.FLAVOR.equals("ccc32c3")) {
                    if (roomName.equals("Saal 1") ||
                            roomName.equals("Saal 2") ||
                            roomName.equals("Saal G") ||
                            roomName.equals("Saal 6")) {
                        roomName = roomName.replace("Saal", "Hall");
                    }
                }
                if (lectureRoom.equalsIgnoreCase(roomName)) {
                    filteredStreams.addAll(room.streams);
                }
            }
        }
        if (filteredStreams.isEmpty()) {
            return Collections.emptyList();
        }
        return filteredStreams;
    }

    public static
    @Nullable
    String getJoinedStreamLinks(@NonNull List<Stream> streams) {
        List<String> streamLinkList = new ArrayList<String>(8);
        for (Stream stream : streams) {
            String streamDisplay = stream.display;
            List<Url> urls = stream.urls;
            if (urls != null && !urls.isEmpty()) {
                for (Url url : urls) {
                    if (url != null) {
                        streamLinkList.add("<a href=\"" + url.url + "\">" +
                                streamDisplay + " (" + url.display + ")</a>");
                    }
                }
            }
        }
        if (streamLinkList.isEmpty()) {
            return null;
        }
        return TextUtils.join("<br>", streamLinkList);
    }

}
