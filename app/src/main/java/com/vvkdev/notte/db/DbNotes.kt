package com.vvkdev.notte.db

import android.provider.BaseColumns

object DbNotes {
    const val DB_VERSION = 1
    const val DB_NOTES = "notes.db"
    const val TABLE_NOTES = "notes"
    const val COLUMN_TITLE = "title"
    const val COLUMN_CONTENT = "content"

    const val CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_NOTES (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY," +
            "$COLUMN_TITLE TEXT," +
            "$COLUMN_CONTENT TEXT)"

    const val DELETE_TABLE = "DROP TABLE IF EXISTS $TABLE_NOTES"
}