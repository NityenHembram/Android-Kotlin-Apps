package com.amvlabs.androidkotlinapps.warecover.room

import androidx.lifecycle.LiveData

class MessageRepository(private val dao: MessageDao) {

    suspend fun insert(messages: Messages){dao.insert(messages)}
    fun getAll():LiveData<List<Messages>>{return dao.getAll()}
    fun getMessage(userName:String):LiveData<List<String>>{return dao.getAllMessage(userName)}
}