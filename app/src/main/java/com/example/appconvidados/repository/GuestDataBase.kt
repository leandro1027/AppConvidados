package com.example.appconvidados.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class GuestDataBase(context: Context): SQLiteOpenHelper(context, NAME, null, VERSION) {

    companion object{
        private const val NAME = "questdb"
        private const val VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}