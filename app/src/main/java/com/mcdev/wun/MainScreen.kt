package com.mcdev.wun

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.hbb20.CountryCodePicker
import com.mcdev.wun.ui.theme.WUNTheme

class MainScreen : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WUNTheme {

                androidx.compose.material.Scaffold { contentPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(contentPadding)
                    ) {
                        Column(modifier = Modifier.align(Alignment.BottomCenter)) {
                            Text(
                                text = "Send a message to anyone on WhatsApp without needing to save their contact",
                                modifier = Modifier.padding(horizontal = 10.dp),
                                fontSize = 15.sp,
                                letterSpacing = 0.sp,
                                lineHeight = 15.sp,
                                textAlign = TextAlign.Center
                            )
                            MyTextInputField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(100.dp)
                                    .padding(horizontal = 10.dp, vertical = 20.dp)
                            )
                        }

                    }
                }

            }
        }
    }
}

@Composable
fun MyTextInputField(modifier: Modifier = Modifier) {
    var text by rememberSaveable {
        mutableStateOf("")
    }
    OutlinedTextField(
        modifier = modifier,
        shape = RoundedCornerShape(size = 10.dp),
        value = text,
        textStyle = androidx.compose.ui.text.TextStyle(
            fontSize = 18.sp,
            color = androidx.compose.ui.graphics.Color.Black,
            textAlign = TextAlign.Start // Center the text (and thus the cursor)
        ),
        singleLine = true,
        onValueChange = {
            text = it
        },
        leadingIcon = {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentWidth(),
                contentAlignment = Alignment.Center
            ) {
                CountryCodePicker(
                    modifier = Modifier.
                    wrapContentSize()
                )
            }
        }
    )
}

@Composable
fun CountryCodePicker(modifier: Modifier) {
    AndroidView(modifier = modifier,
        factory = { context ->
            CountryCodePicker(context).apply {
                this.setAutoDetectedCountry(true)
                this.showCloseIcon(true)
                this.showNameCode(false)
                this.showFlag(true)
                this.setShowPhoneCode(true)
                this.showArrow(true)
                this.contentColor = Color.GRAY
                this.setArrowColor(Color.GRAY)
            }
        },
        update = {

        })
}

@Preview(showBackground = true, showSystemUi = true, uiMode = 0)
@Composable
fun GreetingPreview() {
    WUNTheme {
        MyTextInputField()
    }
}