package com.mcdev.whatsappunsavednumbers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import com.mcdev.whatsappunsavednumbers.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        val root = binding.root
        window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        val countryCode = binding.countryCodePicker.selectedCountryCodeWithPlus
        binding.btnStartChat.setOnClickListener {
            val phoneNumber = countryCode + binding.etInputNumber.text.toString()
            if (phoneNumber.isEmpty()) {
                Toast.makeText(this, "Phone number cannot be empty.", Toast.LENGTH_SHORT).show()
            } else {
                openWhatsApp(phoneNumber)
            }
        }
    }

    private fun openWhatsApp(phoneNumber: String) {
        val url = getString(R.string.whatsapp_send_message_base_url) + phoneNumber
        val openWhatsAppIntent = Intent(Intent.ACTION_VIEW)
        openWhatsAppIntent.data = Uri.parse(url)
        startActivity(openWhatsAppIntent)
    }
}