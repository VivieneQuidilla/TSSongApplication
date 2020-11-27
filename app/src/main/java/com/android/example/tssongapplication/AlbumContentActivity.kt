package com.android.example.tssongapplication

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog

class AlbumDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_content)

        var albumItems: AlbumItem = intent.getSerializableExtra("data") as AlbumItem
        var viewImage = findViewById<ImageView>(R.id.icon_details)
        var viewText = findViewById<TextView>(R.id.icon_name)

        if(albumItems.icons == R.drawable.fearless_album) {
            viewImage.setImageResource(albumItems.icons!!)

            val songsArray = mutableListOf("Fifteen", "Love Story", "White Horse",
                "Forever and Always", "You Belong With Me")
            val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, songsArray)
            val albumSongs = findViewById<ListView>(R.id.album_songs)
            albumSongs.adapter = adapter

            albumSongs.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                val dialogAlert = AlertDialog.Builder(this)
                dialogAlert.setMessage("Do you want to remove this song from list?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                        val removeSong = songsArray[position]
                        songsArray.remove(removeSong)
                        adapter.notifyDataSetChanged()

                    })
                    .setNegativeButton("No", DialogInterface.OnClickListener {
                            dialog, which ->
                        dialog.cancel()
                    })
                val alert = dialogAlert.create()
                alert.setTitle("Removing Song")
                alert.show()
            }// item listener
        }


        else if(albumItems.icons == R.drawable.red_album){
            viewImage.setImageResource(albumItems.icons!!)
            viewText.text = "Red"

            val songsArray =mutableListOf( "Red" , "All to Well", "Begin Again", "Stay Stay Stay",
                "22" , "Everything Has Changed")
            val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, songsArray)
            val albumSongs = findViewById<ListView>(R.id.album_songs)
            albumSongs.adapter = adapter
            albumSongs.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                val dialogAlert = AlertDialog.Builder(this)
                dialogAlert.setMessage("Do you want to remove this song from list?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                        val removeSong = songsArray[position]
                        songsArray.remove(removeSong)
                        adapter.notifyDataSetChanged()

                    })
                    .setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->
                        dialog.cancel()
                    })
                val alert = dialogAlert.create()
                alert.setTitle("Removing Song")
                alert.show()
            }
        }
        else if(albumItems.icons == R.drawable.speak_now_album){
            viewImage.setImageResource(albumItems.icons!!)
            viewText.text = "Speak Now"

            val songsArray = mutableListOf("Back to December", "Speak Now", "If This Was a Movie", "Sparks Fly")
            val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, songsArray)
            val albumSongs = findViewById<ListView>(R.id.album_songs)
            albumSongs.adapter = adapter
            albumSongs.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                val dialogAlert = AlertDialog.Builder(this)
                dialogAlert.setMessage("Do you want to remove this song from list?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                        val removeSong = songsArray[position]
                        songsArray.remove(removeSong)
                        adapter.notifyDataSetChanged()

                    })
                    .setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->
                        dialog.cancel()
                    })
                val alert = dialogAlert.create()
                alert.setTitle("Removing Song")
                alert.show()
            }

        }
    }

}