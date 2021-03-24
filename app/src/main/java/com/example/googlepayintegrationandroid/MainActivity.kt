package com.example.googlepayintegrationandroid

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.googlepayintegrationandroid.Utility.getUpiPaymentUri
import com.example.googlepayintegrationandroid.Utility.makeGpay

class MainActivity : AppCompatActivity() {
    val GOOGLE_PAY_PACKAGE_NAME = "com.google.android.apps.nbu.paisa.user"
    var GOOGLE_PAY_REQUEST_CODE = 123
    var amount: String? = "1"
    var name = "SOUMYA RANJAN"
    var upiId = "soumya.ranjan407@okhdfcbank"
    var transactionNote = "gPAY test"
    var status: String? = null
    var uri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.bt_payme)
        button.setOnClickListener {
            uri = getUpiPaymentUri(name, upiId, transactionNote, amount)
            makeGpay(this@MainActivity, uri!!, GOOGLE_PAY_PACKAGE_NAME, GOOGLE_PAY_REQUEST_CODE)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            status = data.getStringExtra("Status")!!.toLowerCase()
        }
        if (RESULT_OK == resultCode && status == "success") {
            Toast.makeText(this@MainActivity, "Transaction Successful", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this@MainActivity, "Transaction Failed", Toast.LENGTH_SHORT).show()
        }
    }
}