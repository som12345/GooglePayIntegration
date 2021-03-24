package com.example.googlepayintegrationandroid

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast

object Utility {
     fun getUpiPaymentUri(
        name: String,
        upiId: String,
        transactionNote: String,
        amount: String?
    ): Uri? {
        return Uri.Builder()
            .scheme("upi")
            .authority("pay")
            .appendQueryParameter("pa", upiId)
            .appendQueryParameter("pn", name)
            .appendQueryParameter("tn", transactionNote)
            .appendQueryParameter("am", amount)
            .appendQueryParameter("cu", "INR")
            .build()
    }

     fun makeGpay(context: Activity, uri:Uri, packageName:String, requestCode:Int) {
        try {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = uri
            intent.setPackage(packageName)
            context.startActivityForResult(intent, requestCode)
        } catch (e: Exception) {
            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
        }
    }
}