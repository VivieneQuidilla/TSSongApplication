package com.android.example.tssongapplication

class SongTable {
    var id: Int = 0
    var title: String? = null
    var artist: String? = null
    var album: String? = null
    constructor(id: Int = 0, title: String, artist: String, album: String) {
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
    override fun toString(): String {
        return "Title: $(title), Author: $(author), Album: $(album)"
    }
}