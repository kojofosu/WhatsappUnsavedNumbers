package com.mcdev.wun

import InwardCaveShape
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.google.i18n.phonenumbers.Phonenumber
import com.hbb20.CountryCodePicker
import com.mcdev.wun.ui.theme.WUNTheme
import com.mcdev.wun.utils.searchNumberOnWhatsapp
import java.util.Locale

class MainScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val phoneNumberUtil = PhoneNumberUtil.getInstance()
        val rawPhoneNumber: Phonenumber.PhoneNumber = Phonenumber.PhoneNumber()
        enableEdgeToEdge()
        setContent {
            WUNTheme {

                androidx.compose.material.Scaffold { contentPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(contentPadding)
                            .padding(vertical = 50.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Loader(
                                modifier = Modifier
                                    .padding(100.dp)
                                    .height(200.dp)
                                    .width(200.dp)
                            )

                        }
                        Column(modifier = Modifier.align(Alignment.BottomCenter)) {
                            val context = LocalContext.current
                            var text by rememberSaveable {
                                mutableStateOf("")
                            }
                            var countryCodeText by remember {
                                mutableStateOf("")
                            }
                            var visi by remember { mutableStateOf(true) }

                            Text(
                                text = "Send a message to anyone on WhatsApp without needing to save their contact",
                                modifier = Modifier
                                    .padding(horizontal = 10.dp)
                                    .clickable {
                                        visi = !visi
                                    },
                                fontSize = 15.sp,
                                letterSpacing = 0.sp,
                                lineHeight = 15.sp,
                                textAlign = TextAlign.Center
                            )
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {

                                MyTextInputField(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(1f)
                                        .height(100.dp)
                                        .padding(horizontal = 10.dp, vertical = 20.dp),
                                    value = text,
                                    onValueChange = { countryCode, phoneNumber ->
                                        Log.d(
                                            "MainScreen",
                                            "onCreate: countryCode: $countryCode, phoneNumber: $phoneNumber"
                                        )
                                        // removing the plus sign
                                        val countryCodeWithoutPlus =
                                            countryCodeText.replace("+", "").toIntOrNull()
                                        text = phoneNumber
                                        countryCodeText = countryCode
                                        val locale = Locale.getDefault()
                                        rawPhoneNumber.setCountryCode(countryCodeWithoutPlus ?: 1)
                                    }
                                )
//
//                                AnimatedVisibility(visible =  text.isNotBlank()) {
//                                    CountryCodePicker(
//                                        modifier = Modifier
//                                            .wrapContentSize()
//                                            .padding(horizontal = 10.dp, vertical = 20.dp),
//                                        onValueChange = {
//
//                                        }
//                                    )
//                                }

                            }

                            TextButton(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp),
                                shape = RoundedCornerShape(size = 10.dp),
                                contentPadding = PaddingValues(all = 20.dp),
                                onClick = {
                                    val completePhoneNumber = countryCodeText + text
                                    completePhoneNumber.searchNumberOnWhatsapp(context)
                                }) {
                                Text(getString(R.string.start_chat), color = Color.Black)
                            }
                        }
                    }
                }

            }
        }
    }
}

@Composable
fun MyTextInputField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String, String) -> Unit
) {

    var text by rememberSaveable {
        mutableStateOf("")
    }
    var countryCode by remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        modifier = modifier,
        shape = RoundedCornerShape(size = 10.dp),
        value = value,
        label = {
            Text(text = "Phone Number")
        }, colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = colorResource(id = R.color.black), // Outline color when focused
        ),
        textStyle = androidx.compose.ui.text.TextStyle(
            fontSize = 18.sp,
            letterSpacing = 1.sp,
            color = Color.Black,
            background = Color.White,
            textAlign = TextAlign.Start // Center the text (and thus the cursor)
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Number
        ),
        onValueChange = {
            // Ensure only numbers are allowed
            if (it.all { char -> char.isDigit() }) {
                text = it
            }
            onValueChange(countryCode, text)
        },
        leadingIcon = {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentWidth(),
                contentAlignment = Alignment.Center
            ) {
                CountryCodePicker(
                    modifier = Modifier.wrapContentSize(),
                    onValueChange = {
                        countryCode = it
                        onValueChange(countryCode, text)
                    }
                )
            }
        },
        trailingIcon = {
            AnimatedVisibility(
                visible = text.isNotBlank(),
                enter = slideInHorizontally(
                    initialOffsetX = { it },
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                ) + fadeIn(),
                exit = slideOutHorizontally(
                    targetOffsetX = { it },
                    animationSpec = tween(
                        durationMillis = 300
                    )
                ) + fadeOut()
            ) {
                IconButton(onClick = {
                    // Handle send action here
                    text = ""
                }) {
                    Box(
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.primary,
//                                shape = InwardCaveShape(curveRadius = -40f) // Adjust the curveRadius as needed
                            )
                            .padding(16.dp) // Adjust padding as needed
                    ) {
                        Icon(
                            modifier = Modifier
                                .clip(InwardCaveShape(curveRadius = 40f)) // Apply the custom shape
                            ,
                            imageVector = Icons.Default.Send,
                            contentDescription = "Send",
                            tint = Color.White // Adjust color as needed
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun CountryCodePicker(modifier: Modifier, onValueChange: (String) -> Unit) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            CountryCodePicker(context).apply {
                this.setAutoDetectedCountry(true)
                this.showCloseIcon(true)
                this.showNameCode(false)
                this.showFlag(true)
                this.setShowPhoneCode(true)
                this.showArrow(true)
                this.contentColor = R.color.grey
                this.setArrowColor(android.R.color.darker_gray)
            }
        },
        update = {
            it.setOnCountryChangeListener {
                onValueChange(it.selectedCountryCodeWithPlus)
            }
            onValueChange(it.selectedCountryCodeWithPlus)
        })
}

@Composable
fun Loader(modifier: Modifier) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.what_smile))
    val progress by animateLottieCompositionAsState(composition)
    LottieAnimation(
        modifier = modifier,
        composition = composition,
        progress = { progress },
    )
}