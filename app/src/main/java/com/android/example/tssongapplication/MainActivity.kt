package com.android.example.tssongapplication

import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.google.android.material.snackbar.Snackbar
import android.content.Intent as Intent1


val listOfSongs = arrayListOf<String>()


class MainActivity : AppCompatActivity() {

    lateinit var songsListView: ListView
    lateinit var songsTableHandler: SongsTableHandler
    lateinit var song: MutableList<Song>
    lateinit var adapter: ArrayAdapter<Song>

    /*
    private val songsArray = arrayOf("Red", "Back to December", "All to Well",
            "Speak Now", "Begin Again", "Stay Stay Stay" , "22", "Everything Has Changed",
            "Fifteen", "Love Story" , "White Horse", "Forever and Always" ,
            "If This Was a Movie", "Sparks Fly", "You Belong With Me")

     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        songsListView = findViewById(R.id.songsListView)

        songsTableHandler = SongsTableHandler(this)

        song = songsTableHandler.

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, song)
        songsListView.adapter = adapter

        registerForContextMenu(songsListView)

    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.home_menu, menu)
        inflater.inflate(R.menu.database_menu, menu )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.go_to_songs -> {
                true
            }
            R.id.go_to_albums -> {

                startActivity(Intent1(this, AlbumActivity::class.java))
                true
            }
            R.id.go_to_queues -> {

                startActivity(Intent1(this, QueueActivity::class.java))
                true

            }
            R.id.go_to_database ->{
                startActivity(Intent1(this, DatabaseActivity::class.java))
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.song_detail_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        return when(item.itemId){
            R.id.edit_detail -> {

                val song_id = song[info.position]

                val intent = android.content.Intent(applicationContext, EditSongActivity::class.java)
                intent.putExtra("song_id", song_id)

                startActivity(intent)
                true
            }
            R.id.delete_detail -> {
                val song = song[info.position]
                if(songsTableHandler.delete()){
                    song.removeAt(info.position)
                    adapter.notifyDataSetChanged()
                    Toast.makeText(applicationContext, "Song was deleted", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(applicationContext, "Error!", Toast.LENGTH_SHORT).show()
                }
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }
    }

}