package com.mcdev.whatsappunsavednumbers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hbb20.CountryCodePicker;

public class MainActivity extends AppCompatActivity {

    CountryCodePicker countryCodePicker;
    EditText inputNumberEt;
    Button startChatBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        init();



        String countryCode = countryCodePicker.getSelectedCountryCodeWithPlus();

        startChatBtn.setOnClickListener(view -> {
            String phoneNumber = countryCode + inputNumberEt.getText().toString();
            if (phoneNumber.isEmpty()) {
                Toast.makeText(this, "Phone number cannot be empty.", Toast.LENGTH_SHORT).show();
            } else {
                openWhatsApp(phoneNumber);
            }
        });

    }

    private void init() {
        inputNumberEt = findViewById(R.id.etInputNumber);
        startChatBtn = findViewById(R.id.btnStartChat);
        countryCodePicker = findViewById(R.id.countryCodePicker);
    }

    private void openWhatsApp(String phoneNumber) {
        String url = getString(R.string.whatsapp_send_message_base_url) + phoneNumber;

        Intent openWhatsAppIntent = new Intent(Intent.ACTION_VIEW);
        openWhatsAppIntent.setData(Uri.parse(url));
        startActivity(openWhatsAppIntent);
    }
}