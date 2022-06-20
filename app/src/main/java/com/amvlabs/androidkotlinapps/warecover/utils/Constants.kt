package com.amvlabs.androidkotlinapps.warecover.utils


import android.os.Build
import android.os.Environment.*
import java.io.File

object Constants {
    const val ENABLED_NOTIFICATION_LISTENERS = "enabled_notification_listeners"
    const val WHATSAPP = "com.whatsapp"
    const val USER_NAME_KEY = "user_key"
    const val WA_DIR_PATH = "content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fmedia%2Fcom.whatsapp%2FWhatsApp%2FMedia%2F.Statuses"
    const val STORAGE_PERMISSION = "storagePermission"
     var SAVE_DIR = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        "${getStorageDirectory().path}/emulated/0${File.separator}StatusDownloader"
    } else {
        getExternalStorageDirectory().path + File.separator + "StatusDownloader"
    }
}