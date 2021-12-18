package com.mcdev.whatsappunsavednumbers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import com.mcdev.whatsappunsavednumbers.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val root = binding.root
        setContentView(root)
        //window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        binding.btnStartChat.setOnClickListener {
            val countryCode = binding.countryCodePicker.selectedCountryCodeWithPlus
            Log.d(TAG, "onCreate: country code is $countryCode")
            val phoneNumber = countryCode + binding.etInputNumber.text.toString()
            if (phoneNumber.isEmpty() || phoneNumber.isBlank()) {
                Toast.makeText(this, R.string.phone_number_cannot_be_null, Toast.LENGTH_SHORT).show()
            } else {
                openWhatsApp(phoneNumber)
            }
        }
    }

    private fun openWhatsApp(phoneNumber: String) {
        Log.d(TAG, "onCreate: Starting chat with $phoneNumber")
        val url = getString(R.string.whatsapp_send_message_base_url) + phoneNumber
        val openWhatsAppIntent = Intent(Intent.ACTION_VIEW)
        openWhatsAppIntent.data = Uri.parse(url)
        startActivity(openWhatsAppIntent)
    }
}