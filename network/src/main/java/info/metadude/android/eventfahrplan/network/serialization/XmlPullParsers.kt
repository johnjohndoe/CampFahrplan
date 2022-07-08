@file:JvmName("XmlPullParsers")

package info.metadude.android.eventfahrplan.network.serialization

import androidx.annotation.NonNull
import org.xmlpull.v1.XmlPullParser

private const val ZERO_WIDTH_NO_BREAK_SPACE = '\uFEFF'

@NonNull
fun XmlPullParser.getSanitizedText(): String = text?.trim()?.trim(ZERO_WIDTH_NO_BREAK_SPACE) ?: ""
