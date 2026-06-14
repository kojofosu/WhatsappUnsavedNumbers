package com.mcdev.wun.utils

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import androidx.core.net.toUri

fun String.searchNumberOnWhatsapp(context: Context) {
    Log.d("TAG", "onCreate: Starting chat with $this")
    val url = BASE_URL + this
    val openWhatsAppIntent = Intent(Intent.ACTION_VIEW)
    openWhatsAppIntent.data = url.toUri()
    startActivity(context, openWhatsAppIntent, null)
}