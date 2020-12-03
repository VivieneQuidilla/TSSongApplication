package com.android.example.tssongapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

val listOfSongs = arrayListOf<String>()

class   MainActivity : AppCompatActivity() {

    lateinit var songsListView: ListView
    lateinit var songsTableHandler: SongsTableHandler
    lateinit var song: MutableList<Song>
    lateinit var adapter: ArrayAdapter<Song>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        songsListView = findViewById(R.id.songsListView)

        songsTableHandler = SongsTableHandler(this)

        song = songsTableHandler.read()

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, song)
        songsListView.adapter = adapter

        registerForContextMenu(songsListView)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.create_song -> {
                startActivity(Intent(applicationContext, SongActivity::class.java))
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

                val song_id = song[info.position].id

                val intent = Intent(applicationContext, EditSongActivity::class.java)
                intent.putExtra("song_id", song_id)

                startActivity(intent)
                true
            }
            R.id.delete_detail -> {
                val songs = song[info.position]
                if(songsTableHandler.delete(songs)){
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