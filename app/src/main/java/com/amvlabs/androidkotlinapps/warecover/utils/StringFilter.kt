package com.amvlabs.androidkotlinapps.warecover.utils

class StringFilter {
    companion object{
        fun filter(msg: String):String {
            var user = msg
            user = user.replaceAfter(":", "")
            user = user.replace("\\(.*\\)".toRegex(), "").replace(":", "").trim()
            return user
        }
    }
}