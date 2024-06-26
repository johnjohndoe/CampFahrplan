package nerd.tuxmobil.fahrplan.congress.utils

import com.google.common.truth.Truth.assertThat
import nerd.tuxmobil.fahrplan.congress.models.Session
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.of
import org.junit.jupiter.params.provider.MethodSource

class SessionPropertiesFormatterTest {

    private companion object {

        @JvmStatic
        fun getLanguageTextData() = listOf(
            of("-formal", ""),
            of("German", "de"),
            of("german", "de"),
            of("Deutsch", "de"),
            of("deutsch", "de"),
            of("English", "en"),
            of("english", "en"),
            of("Englisch", "en"),
            of("englisch", "en"),
            of("German/English", "de/en"),
        )

    }

    private val formatter = SessionPropertiesFormatter()

    @Test
    fun `getFormattedLinks returns an empty string`() {
        val links = ""
        val expected = ""
        assertThat(formatter.getFormattedLinks(links)).isEqualTo(expected)
    }

    @Test
    fun `getFormattedLinks returns the given unmodified string`() {
        val links = "[VOC projects](https://www.voc.com/projects/)"
        val expected = "[VOC projects](https://www.voc.com/projects/)"
        assertThat(formatter.getFormattedLinks(links)).isEqualTo(expected)
    }

    @Test
    fun `getFormattedLinks returns a br separated string`() {
        val links = "[VOC projects](https://www.voc.com/projects/),[POC](https://poc.com/QXut1XBymAk)"
        val expected = "[VOC projects](https://www.voc.com/projects/)<br>[POC](https://poc.com/QXut1XBymAk)"
        assertThat(formatter.getFormattedLinks(links)).isEqualTo(expected)
    }

    @Test
    fun `getFormattedUrl returns an empty string`() {
        val url = ""
        val expected = ""
        assertThat(formatter.getFormattedUrl(url)).isEqualTo(expected)
    }

    @Test
    fun `getFormattedUrl returns an HTML formatted weblink`() {
        val url = "https://example.com/talk.html"
        val expected = """<a href="https://example.com/talk.html">https://example.com/talk.html</a>"""
        assertThat(formatter.getFormattedUrl(url)).isEqualTo(expected)
    }

    @Test
    fun `getFormattedSpeakers returns empty string if speakers is empty`() {
        val session = createSession(speakers = emptyList())
        assertThat(formatter.getFormattedSpeakers(session)).isEmpty()
    }

    @Test
    fun `getFormattedSpeakers returns single speaker name if speakers contains one name`() {
        val session = createSession(speakers = listOf("Jane Doe"))
        assertThat(formatter.getFormattedSpeakers(session)).isEqualTo("Jane Doe")
    }

    @Test
    fun `getFormattedSpeakers returns speaker names if speakers contains multiple names`() {
        val session = createSession(speakers = listOf("Jane Doe", "John Doe"))
        assertThat(formatter.getFormattedSpeakers(session)).isEqualTo("Jane Doe, John Doe")
    }

    @Test
    fun `getFormattedTrackLanguageText returns track name if language is empty`() {
        val session = createSession(track = "Track", language = "")
        assertThat(formatter.getFormattedTrackLanguageText(session)).isEqualTo("Track")
    }

    @Test
    fun `getFormattedTrackLanguageText returns track name and language if track and language is not empty`() {
        val session = createSession(track = "Track", language = "de-formal")
        assertThat(formatter.getFormattedTrackLanguageText(session)).isEqualTo("Track [de]")
    }

    @Test
    fun `getFormattedTrackLanguageText returns language if track is empty and language is not empty`() {
        val session = createSession(track = "", language = "de-formal")
        assertThat(formatter.getFormattedTrackLanguageText(session)).isEqualTo("[de]")
    }

    @Test
    fun `getLanguageText returns empty string if language is empty`() {
        val session = createSession(language = "")
        assertThat(formatter.getLanguageText(session)).isEmpty()
    }

    @ParameterizedTest(name = """getLanguageText returns "{1}" if language "{0}"""")
    @MethodSource("getLanguageTextData")
    fun `getLanguageText returns two-letter language code`(language: String, expected: String) {
        val session = createSession(language = language)
        assertThat(formatter.getLanguageText(session)).isEqualTo(expected)
    }

    private fun createSession(
        speakers: List<String> = emptyList(),
        track: String = "",
        language: String = "",
    ) = Session(
        sessionId = "",
        speakers = speakers,
        track = track,
        language = language,
    )

}
