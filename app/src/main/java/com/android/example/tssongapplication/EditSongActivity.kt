package com.android.example.tssongapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class EditSongActivity : AppCompatActivity() {

    lateinit var edittitlebutton: Button
    lateinit var editsongtitle: EditText
    lateinit var editsongartist: EditText
    lateinit var editsongalbum: EditText
    lateinit var song: Song

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_song)

        val song_id = intent.getIntExtra("song_id", 0)

        val databaseHandler = SongsTableHandler(this)
        song = databaseHandler.readOne(song_id)

        editsongtitle = findViewById(R.id.edit_song_title)
        editsongartist = findViewById(R.id.edit_song_artist)
        editsongalbum = findViewById(R.id.edit_song_album)

        editsongtitle.setText(song.title)
        editsongartist.setText(song.artist)
        editsongalbum.setText(song.album)

        edittitlebutton.setOnClickListener{
            val title = editsongtitle.text.toString()
            val artist = editsongartist.text.toString()
            val album = editsongalbum.text.toString()

            val edit_song = Song(id = song.id, title = title, artist = artist, album = album)

            if(databaseHandler.update(edit_song)){
                Toast.makeText(applicationContext, "Song was edited", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Error!", Toast.LENGTH_SHORT).show()
            }
        }

    }
}