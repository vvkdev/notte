package com.vvkdev.notte.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class DbManager(context: Context) {
    private val dbHelper = DbHelper(context)
    private val db: SQLiteDatabase by lazy<SQLiteDatabase> { dbHelper.writableDatabase }

    fun insertToDb(title: String, content: String) {
        val values = ContentValues().apply {
            put(DbNotes.COLUMN_TITLE, title)
            put(DbNotes.COLUMN_CONTENT, content)
        }
        db.insert(DbNotes.TABLE_NOTES, null, values)
    }

    fun readDb(): MutableList<Note> {
        val dataList = mutableListOf<Note>()
        val cursor = db.query(
            DbNotes.TABLE_NOTES,
            null, null, null, null, null,
            DbNotes.COLUMN_TITLE
        )
        with(cursor) {
            while (moveToNext()) {
                val title = cursor.getString(cursor.getColumnIndexOrThrow(DbNotes.COLUMN_TITLE))
                val content = cursor.getString(cursor.getColumnIndexOrThrow(DbNotes.COLUMN_CONTENT))
                dataList.add(Note(title, content))
            }
            cursor.close()
            return dataList
        }
    }

    fun closeDb() = dbHelper.close()
}