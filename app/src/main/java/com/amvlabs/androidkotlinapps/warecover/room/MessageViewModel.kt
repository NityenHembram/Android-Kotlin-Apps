package com.amvlabs.androidkotlinapps.warecover.room

import android.app.Application
import android.icu.util.Calendar
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.amvlabs.androidkotlinapps.warecover.services.NotificationService
import com.amvlabs.androidkotlinapps.warecover.services.OnNotificationReceive
import com.amvlabs.androidkotlinapps.warecover.utils.StringFilter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class MessageViewModel(application: Application):AndroidViewModel(application),
    OnNotificationReceive {
    lateinit var repo: MessageRepository
    lateinit var allData:LiveData<List<Messages>>
    lateinit var allMessage:LiveData<List<String>>

    init {
        val db = MessageDatabase.getInstance(application)
        val dao = db.dao()
        repo = MessageRepository(dao)
        allData  = repo.getAll()
        NotificationService.setOnNotificaitonListener(this)
    }

    private suspend fun insert(messages: Messages){repo.insert(messages)}
    fun getAllMessage(userName: String):LiveData<List<String>>{return repo.getMessage(userName)}

    override fun onReceive(userName: String, message: String) {
        val currentDate = Calendar.getInstance().time
        val message = Messages(StringFilter.filter(userName),message,currentDate)
        GlobalScope.launch {
            insert(message)
        }
    }

}