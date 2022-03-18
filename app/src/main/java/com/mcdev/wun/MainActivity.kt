package com.mcdev.wun

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.util.Log
import com.mcdev.wun.databinding.ActivityMainBinding
import com.mcdev.wun.utils.BASE_URL

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val root = binding.root
        setContentView(root)

        binding.countryCodePicker.apply {
            contentColor = Color.GRAY
            setArrowColor(Color.GRAY)
        }

        binding.btnStartChat.setOnClickListener {
            val countryCode = binding.countryCodePicker.selectedCountryCodeWithPlus
            Log.d(TAG, "onCreate: country code is $countryCode")
            val restOfPhoneNumber = binding.etInputNumber.text.toString()
            val phoneNumber = countryCode + restOfPhoneNumber
            if (restOfPhoneNumber.isEmpty() || restOfPhoneNumber.isBlank()) {
                Toast.makeText(this, R.string.phone_number_cannot_be_null, Toast.LENGTH_SHORT).show()
            } else {
                try {
                    openWhatsApp(phoneNumber)
                } catch (e: Exception) {
                    Log.e(TAG, "onCreate: Error occurred.", e)
                    Toast.makeText(applicationContext, "Error occurred. Try again with a valid number", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun openWhatsApp(phoneNumber: String) {
        Log.d(TAG, "onCreate: Starting chat with $phoneNumber")
        val url = BASE_URL + phoneNumber
        val openWhatsAppIntent = Intent(Intent.ACTION_VIEW)
        openWhatsAppIntent.data = Uri.parse(url)
        startActivity(openWhatsAppIntent)
    }
}