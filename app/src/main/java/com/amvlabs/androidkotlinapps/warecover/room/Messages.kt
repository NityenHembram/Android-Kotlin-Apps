package com.amvlabs.androidkotlinapps.warecover.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "message_table")
class Messages() {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var userName: String = ""
    var message: String = ""
    lateinit var date: Date

    constructor(userName: String, message: String, date: Date) : this() {
        this.userName = userName
        this.message = message
        this.date = date
    }
    constructor(userName: String, message: String):this(){
        this.userName = userName
        this.message = message
    }


}

