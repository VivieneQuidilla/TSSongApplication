package com.android.example.tssongapplication

import android.content.DialogInterface
import android.database.DatabaseErrorHandler
import android.database.DatabaseUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class AddNewRecord : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_song)

    }

        fun saveRecord(view: View) {
            val title = findViewById<TextView>(R.id.textTitle)
            val artist = findViewById<TextView>(R.id.textArtist)
            val album = findViewById<TextView>(R.id.textAlbum)
            val databaseHandler: DatabaseHandler = DatabaseHandler(this)
            val status = databaseHandler.AddNewRecord(SongModel(title, artist, album))
            if (status > -1) {
                Toast.makeText(applicationContext, "Record saved", Toast.LENGTH_LONG).show()
                title.text.clear()
                artist.text.clear()
                album.text.clear()
            } else {
                Toast.makeText(applicationContext, "Fields can't be blank", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    fun viewRecord(view: View): List<SongModel> {
        val databaseHandler: DatabaseErrorHandler = DatabaseErrorHandler {
            val song: List<SongModel> = viewRecord(view)
            val songArrayID = Array<String>(song.size) { "0" }
            val songArrayTitle = Array<String>(song.size) { "null" }
            val songArrayArtist = Array<String>(song.size) { "null" }
            val songArrayAlbum = Array<String>(song.size) { "null" }

            var index = 0

            for (e in song) {
                songArrayTitle[index] = e.title
                songArrayArtist[index] = e.artist
                songArrayAlbum[index] = e.album
                index++
            }

            val adapter =
                AdapterView(this, songArrayID, songArrayTitle, songArrayArtist, songArrayAlbum)
            listView.adapter = adapter
        }
    }

    fun updateRecord(view: View) {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.update_dialog, null)
        dialogBuilder.setView(dialogView)

        val editTitle = dialogView.findViewById(R.id.textArtist) as EditText
        val editArtist = dialogView.findViewById(R.id.textArtist) as EditText
        val editAlbum = dialogView.findViewById(R.id.textAlbum) as EditText

        dialogBuilder.setTitle("Update Record")
        dialogBuilder.setPositiveButton("Update", DialogInterface.OnClickListener { _, _ ->

            val updateSong = editTitle.text.toString()
            val updateArtist = editArtist.text.toString()
            val updateAlbum = editAlbum.text.toString()
            val databaseHandler: DatabaseErrorHandler = DatabaseErrorHandler(this)

            val status = databaseHandler.updateSong(SongModel(title, artist, album))
            if (status > -1) {
                Toast.makeText(this, "Record updated", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Fields can't be blank", Toast.LENGTH_LONG).show()
            }
        })
        dialogBuilder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
        })
        val b = dialogBuilder.create()
        b.show()
    }
}

