package com.android.example.tssongapplication

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DatabaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database)
        val add_song = findViewById<TextView>(R.id.add_song)
        add_song.setOnClickListener {
            val dbHandler = OpenHelper(this, null)
            val song_name = findViewById<EditText>(R.id.song_name)
            val song_artist = findViewById<EditText>(R.id.song_artist)
            val song_album = findViewById<EditText>(R.id.song_album)
            val song = Song(song_name.text.toString(),song_artist.text.toString(), song_album.text.toString())
            dbHandler.addSong(song)
            Toast.makeText(this, song_name.text.toString() + " Added to Songs", Toast.LENGTH_LONG).show()
        }
        val edit_song = findViewById<TextView>(R.id.edit_song)
        edit_song.setOnClickListener {
            val dbHandler = OpenHelper(this, null)
            val song_name = findViewById<EditText>(R.id.song_name)
            val song_artist = findViewById<EditText>(R.id.song_artist)
            val song_album = findViewById<EditText>(R.id.song_album)
            val edit = Song(song_name.text.toString(),song_artist.text.toString(), song_album.text.toString())
            dbHandler.editSong(edit)
            Toast.makeText(this, song_name.text.toString() + " Edited to Songs", Toast.LENGTH_LONG).show()
        }
        val delete_song = findViewById<TextView>(R.id.delete_song)
        delete_song.setOnClickListener {
            val dbHandler = OpenHelper(this, null)
            val song_name = findViewById<EditText>(R.id.song_name)
            val song_artist = findViewById<EditText>(R.id.song_artist)
            val song_album = findViewById<EditText>(R.id.song_album)
            val delete = Song(song_name.text.toString(),song_artist.text.toString(), song_album.text.toString())
            dbHandler.deleteSong(delete)
            Toast.makeText(this, song_name.text.toString() + " Deleted from Songs", Toast.LENGTH_LONG).show()
        }
        val show_song = findViewById<Button>(R.id.show_song)
        show_song.setOnClickListener {
            val song_display = findViewById<TextView>(R.id.song_display)
            song_display.text = ""
            val list: ArrayList<String> = ArrayList()
            val dbHandler = OpenHelper(this, null)
            val cursor = dbHandler.showSongs()
            val values = cursor.toArrayList<String>()
            list.add("text")
            cursor!!.moveToFirst()
            ((cursor.getString(cursor.getColumnIndex(OpenHelper.COLUMN_ARTIST))))
            while (cursor.moveToNext()) {
                ((cursor.getString(cursor.getColumnIndex(OpenHelper.COLUMN_ARTIST))))
            }
            cursor.close()
            cursor.moveToFirst()
            val list = generateSequence {
                cursor.MoveToNext()
                getStringFromCursor(cursor)
            }.take(cursor.count).toList()
        }
    }
}

class Song {
    var id: Int = 0
    var title: String? = null
    var artist: String? = null
    var album: String? = null
    constructor(id: Int, title: String, artist: String, album: String) {
        this.id = id
        this.title = title
        this.artist = artist
        this.album = album
    }
    constructor (title: String, artist: String, album: String) {
        this.title = title
        this.artist = artist
        this.album = album
    }
}

class OpenHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_PRODUCTS_TABLE = ("CREATE TABLE " +
                TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_TITLE
                + " TEXT" + ")")
        db.execSQL(CREATE_PRODUCTS_TABLE)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP SONG IF EXISTING " + TABLE_NAME)
        onCreate(db)
    }
    fun addSong(name: Song): Long {
        val db = this.writableDatabase
        val contentvalues = ContentValues()
        contentvalues.put(COLUMN_TITLE, name.title)
        contentvalues.put(COLUMN_ARTIST, name.artist)
        contentvalues.put(COLUMN_ALBUM, name.album)
        val success = db.insert(TABLE_NAME, null, contentvalues)
        db.close()
        return success
    }
    fun editSong(name: Song): Int {
        val db = this.writableDatabase
        val contentvalues = ContentValues()
        contentvalues.put(COLUMN_TITLE, name.title)
        val success = db.update(COLUMN_TITLE, contentvalues, "id = " + name.title, null)
        db.close()
        return success
    }
    fun deleteSong(name: Song): Int {
        val db = this.writableDatabase
        val contentvalues = ContentValues()
        contentvalues.put(COLUMN_TITLE, name.title)
        val success = db.delete(COLUMN_TITLE, "id = " + name.title, null)
        db.close()
        return success
    }
    fun showSongs(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "songlist.db"
        val TABLE_NAME = "Songs"
        val COLUMN_ID = " id"
        val COLUMN_TITLE = "title"
        val COLUMN_ARTIST = "artist"
        val COLUMN_ALBUM = "album"
    }
    fun <T> Cursor.toArrayList(block: (Cursor) -> T) : ArrayList<T> {
        return arrayListOf<T>().also { list ->
            if (moveToFirst()) {
                do {

                    list.add(block.invoke(this))
                } while (moveToNext())
            }
        }
    }
    cursor.moveToFirst()
    val list = generateSequence {
        cursor.MoveToNext()
        getStringFromCursor(cursor)
    }.take(cursor.count).toList()
}


