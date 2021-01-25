package com.mcdev.whatsappunsavednumbers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText inputNumberEt;
    Button startChatBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();



        startChatBtn.setOnClickListener(view -> {
            String phoneNumber = inputNumberEt.getText().toString();
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
    }

    private void openWhatsApp(String phoneNumber) {
        String url = "https://api.whatsapp.com/send?phone=" + phoneNumber;

        Intent openWhatsAppIntent = new Intent(Intent.ACTION_VIEW);
        openWhatsAppIntent.setData(Uri.parse(url));
        startActivity(openWhatsAppIntent);
    }
}