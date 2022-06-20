package com.amvlabs.androidkotlinapps.emicalculator

import android.os.Parcel
import android.os.Parcelable

data class Transection(
    var openingBalance:String,
    var emi: String?,
    val IntPaidYearly:String,
    var principalPainYearly:String,
    var closingBalance:String
)