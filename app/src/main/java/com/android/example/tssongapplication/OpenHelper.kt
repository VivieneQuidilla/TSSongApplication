package com.android.example.tssongapplication

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper


class OpenHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "songlist.db"
        val TABLE_NAME = "Songs"
        val COLUMN_ID = " id"
        val COLUMN_TITLE = "title"
        val COLUMN_ARTIST = "artist"
        val COLUMN_ALBUM = "album"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val query = ("CREATE TABLE "
                + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_TITLE + " TEXT"
                + COLUMN_ARTIST + " TEXT"
                + COLUMN_ALBUM + " TEXT " + ")" )
        db.execSQL(query)
        }


    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP SONG IF EXISTING " + TABLE_NAME)
        onCreate(db)
    }

    fun addSong(song: SongTable): Boolean {
        val db = this.writableDatabase
        val contentvalues = ContentValues()
        contentvalues.put(COLUMN_TITLE, song.title)
        contentvalues.put(COLUMN_ARTIST, song.artist)
        contentvalues.put(COLUMN_ALBUM, song.album)
        val res = db.insert(TABLE_NAME, null, contentvalues)
        db.close()
        if (res == (0).toLong()) {
            return true
        }
        return false
    }

    fun read():MutableList<SongTable> {
        val list: MutableList<SongTable> = ArrayList()
        val query = "SELECT + FROM "+ TABLE_NAME
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(query, null)
        } catch (e: SQLiteException) {
            return list
        }
        var id: Int = 0
        var title: String? = null
        var artist: String? = null
        var album: String? = null
        if(cursor.moveToFirst()){
            do {
                id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                title = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE))
                artist = cursor.getString(cursor.getColumnIndex(COLUMN_ARTIST))
                album = cursor.getString(cursor.getColumnIndex(COLUMN_ALBUM))
                val song = SongTable(id, title, artist, album)
                list.add(song)
            } while (cursor.moveToNext())
        }
        return list
    }

    /*
    fun editSong(song: SongTable): Boolean {
        val db = this.writableDatabase
        val contentvalues = ContentValues()
        contentvalues.put(COLUMN_TITLE, song.title)
        contentvalues.put(COLUMN_ARTIST, song.artist)
        contentvalues.put(COLUMN_ALBUM, song.album)
        val res = db.update(COLUMN_TITLE, contentvalues, "id = " + name.title, null)
        db.close()
        if (res == (0).toLong()) {
            return true
        }
        return false
    }
    fun deleteSong(song: SongTable): Boolean {
        val db = this.writableDatabase
        val contentvalues = ContentValues()
        contentvalues.put(COLUMN_TITLE, song.title)
        contentvalues.put(COLUMN_ARTIST, song.artist)
        contentvalues.put(COLUMN_ALBUM, song.album)
        val res = db.update(COLUMN_TITLE, contentvalues, "id = " + song.title, null)
        db.close()
        if (res == (0).toLong()) {
            return false
        }
        return false
    }
    fun showSongs(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }

    }
*/
}