package com.android.example.tssongapplication

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.system.Os.read
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DatabaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database)

        val songName = findViewById<EditText>(R.id.song_name)
        val songArtist = findViewById<EditText>(R.id.song_artist)
        val songAlbum = findViewById<EditText>(R.id.song_album)

        val add_song = findViewById<TextView>(R.id.add_song)

        add_song.setOnClickListener {
            val dbHandler = OpenHelper(this)
            val song = SongTable(
                songName.text.toString(),
                songArtist.text.toString(),
                songAlbum.text.toString()
            )
            if (dbHandler.addSong(song)) {
                Toast.makeText(
                    this,
                    songName.text.toString() + " Added to Songs",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                Toast.makeText(
                    this,
                    songName.text.toString() + " Not Added to Songs",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
 /*
        val edit_song = findViewById<TextView>(R.id.edit_song)
        edit_song.setOnClickListener {
            val dbHandler = OpenHelper(this, null)
            val edit = SongTable(
                songName.text.toString(),
                songArtist.text.toString(),
                songAlbum.text.toString()
            )
            dbHandler.editSong(edit)
            Toast.makeText(this, songName.text.toString() + " Edited to Songs", Toast.LENGTH_LONG)
                .show()
        }
        val delete_song = findViewById<TextView>(R.id.delete_song)
        delete_song.setOnClickListener {
            val dbHandler = OpenHelper(this, null)
            val delete = SongTable(
                songName.text.toString(),
                songArtist.text.toString(),
                songAlbum.text.toString()
            )
            dbHandler.deleteSong(delete)
            Toast.makeText(
                this,
                songName.text.toString() + " Deleted from Songs",
                Toast.LENGTH_LONG
            ).show()
        }

        fun clearFields() {
            songName.text.clear()
            songArtist.text.clear()
            songAlbum.text.clear()
        }

  */
    }


}





