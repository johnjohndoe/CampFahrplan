package info.metadude.android.eventfahrplan.database.sqliteopenhelper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import info.metadude.android.eventfahrplan.database.contract.FahrplanContract.MetasTable.Columns.NUM_DAYS
import info.metadude.android.eventfahrplan.database.contract.FahrplanContract.MetasTable.Columns.SCHEDULE_ETAG
import info.metadude.android.eventfahrplan.database.contract.FahrplanContract.MetasTable.Columns.SCHEDULE_GENERATOR_NAME
import info.metadude.android.eventfahrplan.database.contract.FahrplanContract.MetasTable.Columns.SCHEDULE_GENERATOR_VERSION
import info.metadude.android.eventfahrplan.database.contract.FahrplanContract.MetasTable.Columns.SCHEDULE_LAST_MODIFIED
import info.metadude.android.eventfahrplan.database.contract.FahrplanContract.MetasTable.Columns.SUBTITLE
import info.metadude.android.eventfahrplan.database.contract.FahrplanContract.MetasTable.Columns.TIME_ZONE_NAME
import info.metadude.android.eventfahrplan.database.contract.FahrplanContract.MetasTable.Columns.TITLE
import info.metadude.android.eventfahrplan.database.contract.FahrplanContract.MetasTable.Columns.VERSION
import info.metadude.android.eventfahrplan.database.contract.FahrplanContract.MetasTable.NAME
import info.metadude.android.eventfahrplan.database.extensions.addTextColumn

internal class MetaDBOpenHelper(context: Context) : SQLiteOpenHelper(
    context.applicationContext,
    DATABASE_NAME,
    null,
    DATABASE_VERSION,
) {

    private companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "meta"

        // language=sql
        const val META_TABLE_CREATE = "CREATE TABLE $NAME (" +
                "$NUM_DAYS INTEGER, " +
                "$VERSION TEXT, " +
                "$TITLE TEXT, " +
                "$SUBTITLE TEXT, " +
                "$SCHEDULE_ETAG TEXT, " +
                "$TIME_ZONE_NAME TEXT, " +
                "$SCHEDULE_LAST_MODIFIED TEXT DEFAULT ''," +
                "$SCHEDULE_GENERATOR_NAME TEXT DEFAULT NULL," +
                "$SCHEDULE_GENERATOR_VERSION TEXT DEFAULT NULL" +
                ");"
    }

    override fun onCreate(db: SQLiteDatabase) = with(db) {
        execSQL(META_TABLE_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) = Unit

}
