package com.amvlabs.androidkotlinapps.messagesender

import android.content.Context
import androidx.appcompat.app.AlertDialog

class MyDialog(context: Context) {
    private val dialog = AlertDialog.Builder(context)
    private var isDialogShowing = false

    fun setDialog(title: String, message: String): AlertDialog.Builder {
        dialog.apply {
            setTitle(title)
            setMessage(message)
        }
        return dialog
    }

    fun showDialog() {
        if (!isDialogShowing) {
            dialog.show()
        } else {
            isDialogShowing = true
        }
    }

    fun hideDialog() {
        if (isDialogShowing) {
            dialog.show().hide()
        }
    }
}