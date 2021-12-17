package com.mcdev.whatsappunsavednumbers

import androidx.appcompat.app.AppCompatActivity
import com.hbb20.CountryCodePicker
import android.widget.EditText
import android.os.Bundle
import com.mcdev.whatsappunsavednumbers.R
import android.view.WindowManager
import android.widget.Toast
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    var countryCodePicker: CountryCodePicker? = null
    var inputNumberEt: EditText? = null
    var startChatBtn: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        init()
        val countryCode = countryCodePicker!!.selectedCountryCodeWithPlus
        startChatBtn!!.setOnClickListener { view: View? ->
            val phoneNumber = countryCode + inputNumberEt!!.text.toString()
            if (phoneNumber.isEmpty()) {
                Toast.makeText(this, "Phone number cannot be empty.", Toast.LENGTH_SHORT).show()
            } else {
                openWhatsApp(phoneNumber)
            }
        }
    }

    private fun init() {
        inputNumberEt = findViewById(R.id.etInputNumber)
        startChatBtn = findViewById(R.id.btnStartChat)
        countryCodePicker = findViewById(R.id.countryCodePicker)
    }

    private fun openWhatsApp(phoneNumber: String) {
        val url = getString(R.string.whatsapp_send_message_base_url) + phoneNumber
        val openWhatsAppIntent = Intent(Intent.ACTION_VIEW)
        openWhatsAppIntent.data = Uri.parse(url)
        startActivity(openWhatsAppIntent)
    }
}