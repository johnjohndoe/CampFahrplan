package info.metadude.android.eventfahrplan.database.sqliteopenhelper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import info.metadude.android.eventfahrplan.database.contract.FahrplanContract.AlarmsTable.Columns.DAY_INDEX
import info.metadude.android.eventfahrplan.database.contract.FahrplanContract.AlarmsTable.Columns.ID
import info.metadude.android.eventfahrplan.database.contract.FahrplanContract.AlarmsTable.Columns.SESSION_ID
import info.metadude.android.eventfahrplan.database.contract.FahrplanContract.AlarmsTable.Columns.SESSION_TITLE
import info.metadude.android.eventfahrplan.database.contract.FahrplanContract.AlarmsTable.Columns.TIME
import info.metadude.android.eventfahrplan.database.contract.FahrplanContract.AlarmsTable.NAME

internal class AlarmsDBOpenHelper(context: Context) : SQLiteOpenHelper(
    context.applicationContext,
    DATABASE_NAME,
    null,
    DATABASE_VERSION,
) {

    private companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "alarms"

        // language=sql
        const val ALARMS_TABLE_CREATE = "CREATE TABLE $NAME (" +
                "$ID INTEGER PRIMARY KEY, " +
                "$SESSION_TITLE TEXT, " +
                "$TIME INTEGER, " +
                "$SESSION_ID INTEGER, " +
                "$DAY_INDEX INTEGER" +
                ");"
    }

    override fun onCreate(db: SQLiteDatabase) = with(db) {
        execSQL(ALARMS_TABLE_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) = Unit

}
