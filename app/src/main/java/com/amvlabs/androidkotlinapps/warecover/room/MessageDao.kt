package com.amvlabs.androidkotlinapps.warecover.room
import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface MessageDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(messages: Messages)

    @Delete
    suspend fun deleteAll(messages: Messages)

    @Query("SELECT * FROM message_table")
    fun getAll(): LiveData<List<Messages>>

    @Query("SELECT message FROM message_table WHERE userName =:userName")
    fun getAllMessage(userName: String): LiveData<List<String>>


}