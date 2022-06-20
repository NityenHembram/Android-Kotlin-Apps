package com.amvlabs.androidkotlinapps.messagesender

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import android.widget.Toast
import java.net.URLEncoder

class Sender(private val context: Context) {
     fun openWhatsApp(number:String,msg: String) {
        val isWhatsappInstalled = whatsappInstalledOrNot("com.whatsapp")
        if (isWhatsappInstalled) {
            sendMsg(number,msg)
        } else {
            val uri = Uri.parse("market://details?id=com.whatsapp")
            val goToMarket = Intent(Intent.ACTION_VIEW, uri)
            Toast.makeText(
                context, "WhatsApp not Installed",
                Toast.LENGTH_SHORT
            ).show()
            context.startActivity(goToMarket)
        }
    }

    private fun sendMsg(mobile: String, msg: String){
        try {
            val packageManager = context.packageManager
            val i = Intent(Intent.ACTION_VIEW)
            val url =
                "https://wa.me/$mobile" + "?text=" + URLEncoder.encode(msg, "utf-8")
            i.setPackage("com.whatsapp")
            i.data = Uri.parse(url)
            if (i.resolveActivity(packageManager) != null) {
                context.startActivity(i)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun whatsappInstalledOrNot(uri: String): Boolean {
        val pm = context.packageManager
        var app_installed = false
        app_installed = try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
        return app_installed
    }
}