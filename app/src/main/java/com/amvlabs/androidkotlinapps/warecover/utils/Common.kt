package com.amvlabs.androidkotlinapps.warecover.utils

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.FileUtils
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import com.google.android.material.snackbar.Snackbar
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class Common(var ctx: Context, var view: View) {

    @RequiresApi(Build.VERSION_CODES.Q)
    fun saveFile(list: ArrayList<Uri>, adapterPosition: Int, ctx: Context){
        val file = File(Constants.SAVE_DIR)
        val imagePath = list[adapterPosition]
        val path = "${imagePath.lastPathSegment?.replace("primary:","/storage/emulated/0/")}"
        val imageFilePath = File(imagePath.toString())
        Log.d("save", "saveFile: $path")

        if(!file.exists()){
            if(!file.mkdir()){
                Snackbar.make(ctx,view,"Something went wrong", Snackbar.LENGTH_SHORT).show()
            }
        }

        val simpleDateFormat = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
        val currentDateAndTime = simpleDateFormat.format(Date())

        val fileName = "IMG_$currentDateAndTime.jpg"
        val imageUri = FileProvider.getUriForFile(
            ctx,"com.amvlabs.warecover.provider" , //(use your app signature + ".provider" )
            File(imagePath.toString())
        )

        val value = ContentValues()
        value.put(MediaStore.MediaColumns.DISPLAY_NAME,fileName)
        value.put(MediaStore.MediaColumns.MIME_TYPE,"image/jpg")
        val destFile = "content://$file".toUri()
        val my = ctx.contentResolver.insert(destFile,value)

        val inputStream = ctx.contentResolver.openInputStream(imageUri)
        val outputStream = ctx.contentResolver.openOutputStream(my!!)

        FileUtils.copy(inputStream!!,outputStream!!)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//            org.apache.commons.io.FileUtils.copyFile(imageFilePath,destFile)
//            Snackbar.make(ctx,view,"Saved", Snackbar.LENGTH_SHORT).show()
//            SingleMediaScanner(ctx,file)
//        }
    }

    fun shareImage(list: ArrayList<Uri>, adapterPosition: Int){
        val imagePath = list[adapterPosition]
        val path = "${imagePath.lastPathSegment?.replace("primary:","/storage/emulated/0/")}"

        val imageUri = FileProvider.getUriForFile(
            ctx,"com.amvlabs.warecover.provider" , //(use your app signature + ".provider" )
            File(imagePath.toString())
        )

        Log.d("TAG", "shareImage: $imageUri")

        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "image/jpg"
        shareIntent.putExtra(Intent.EXTRA_STREAM,Uri.parse(imageUri.toString()))
        ctx.startActivity(shareIntent)
    }
}