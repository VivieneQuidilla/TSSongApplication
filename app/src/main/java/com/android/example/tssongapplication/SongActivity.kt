package com.android.example.tssongapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SongActivity : AppCompatActivity() {

    lateinit var addtitlebutton: Button
    lateinit var songtitle: EditText
    lateinit var songartist: EditText
    lateinit var songalbum: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song)

        val databaseHandler = SongsTableHandler(this)
        songtitle = findViewById(R.id.song_title)
        songartist = findViewById(R.id.song_artist)
        songalbum = findViewById(R.id.song_album)

        addtitlebutton = findViewById(R.id.add_title)
        addtitlebutton.setOnClickListener {
            val title = songtitle.text.toString()
            val artist = songartist.text.toString()
            val album = songalbum.text.toString()

            val song = Song(title = title, artist =  artist, album = album)

            if(databaseHandler.create(song)){
                Toast.makeText(applicationContext, "Song was added", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Error!", Toast.LENGTH_SHORT).show()
            }
            clearFields()
        }
    }
    fun clearFields(){
        songtitle.text.clear()
        songartist.text.clear()
        songalbum.text.clear()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.display_song -> {
                startActivity(Intent(applicationContext, MainActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
