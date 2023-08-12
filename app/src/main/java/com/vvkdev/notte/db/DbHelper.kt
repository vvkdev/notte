package com.vvkdev.notte.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(context: Context) :
    SQLiteOpenHelper(context, DbNotes.DB_NOTES, null, DbNotes.DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(DbNotes.CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL(DbNotes.DELETE_TABLE)
        onCreate(db)
    }
}