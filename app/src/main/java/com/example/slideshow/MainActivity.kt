package com.example.slideshow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val SoftPink = Color(0xFFFFF2F7)
val Lavender = Color(0xFFECE1F4)
val DarkLavender = Color(0xFF9275A6)
val RosePink = Color(0xFFD9A5BE)
val SoftWhite = Color(0xFFFFFCFE)
val TextColor = Color(0xFF806B85)

@Composable
fun GalleryTheme(content: @Composable () -> Unit) {
    MaterialTheme {
        content()
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GalleryTheme {
                SlideshowApp()
            }
        }
    }
}

@Composable
fun SlideshowApp() {
    var started by remember { mutableStateOf(false) }

    val background = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFFFF2F7),
            Color(0xFFF2EDFA),
            Color(0xFFFFF9FC)
        )
    )

    Box(
        modifier = Modifier.fillMaxSize().background(background),
        contentAlignment = Alignment.Center
    ) {
        if (!started) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(24.dp).clip(RoundedCornerShape(30.dp))
                    .background(SoftWhite)
                    .border(
                        1.dp,
                        Lavender,
                        RoundedCornerShape(30.dp)
                    )
                    .padding(28.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "୨୧",
                    fontSize = 36.sp,
                    color = RosePink
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Game Collection Gallery",
                    fontSize = 28.sp,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    color = DarkLavender,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text =  "♡",
                    fontSize = 14.sp,
                    color = TextColor,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(28.dp))

                Button(
                    onClick = { started = true },
                    modifier = Modifier.fillMaxWidth(0.85f).height(52.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = DarkLavender
                    )
                ) {
                    Text(
                        text = "Enter the Game Collection",
                        fontSize = 16.sp
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "✧ made with love ✧",
                    color = RosePink,
                    fontSize = 12.sp
                )
            }
        } else {
            SlideshowScreen(onBack = { started = false })
        }
    }
}

@Composable
fun SlideshowScreen(onBack: () -> Unit = {}) {
    val images = listOf(
        R.drawable.slide1,
        R.drawable.slide2,
        R.drawable.slide3,
        R.drawable.slide4,
        R.drawable.slide5
    )

    val captions = listOf(
        "genshin impact ♡",
        "wuthering waves ✧",
        "honkai starrail ♡",
        "infinity nikki ✧",
        "persona 5 royal ♡",
    )

    var currentSlide by remember { mutableIntStateOf(0) }
    var slideInput by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().background(SoftPink).padding(horizontal = 20.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            TextButton(
                onClick = onBack,
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.textButtonColors(
                    contentColor = DarkLavender
                )
            ) {
                Text(
                    text = "← Back",
                    fontSize = 14.sp,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "my game collection",
            fontSize = 27.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            color = DarkLavender,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "a collection of favorite games ♡ ✧",
            fontSize = 13.sp,
            color = TextColor
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(26.dp))
                .background(SoftWhite)
                .border(
                    1.dp,
                    Lavender,
                    RoundedCornerShape(26.dp)
                )
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier.size(240.dp).clip(RoundedCornerShape(18.dp))
                    .background(Lavender)
                    .border(
                        2.dp, RosePink, RoundedCornerShape(18.dp)
                    )
                    .padding(4.dp).clip(RoundedCornerShape(14.dp))
            ) {
                Image(
                    painter = painterResource(id = images[currentSlide]),
                    contentDescription = captions[currentSlide],
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = captions[currentSlide],
                fontSize = 21.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                color = DarkLavender,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "୨୧  slide ${currentSlide + 1} of ${images.size}  ୨୧",
                fontSize = 13.sp,
                color = RosePink
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = slideInput,
                onValueChange = {
                    slideInput = it
                    errorMessage = ""
                },
                label = { Text("Slide number") },
                placeholder = { Text("1 - ${images.size}") },
                modifier = Modifier.weight(1f),
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = DarkLavender,
                    unfocusedBorderColor = RosePink,
                    focusedLabelColor = DarkLavender,
                    cursorColor = DarkLavender
                )
            )

            Spacer(modifier = Modifier.width(10.dp))

            Button(
                onClick = {
                    val number = slideInput.toIntOrNull()

                    if (number != null && number in 1..images.size) {
                        currentSlide = number - 1
                        slideInput = ""
                        errorMessage = ""
                    } else {
                        errorMessage =
                            "Please enter 1 to ${images.size} ♡"
                    }
                },
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = DarkLavender
                ),
                modifier = Modifier.height(56.dp)
            ) {
                Text("Go ♡")
            }
        }

        if (errorMessage.isNotEmpty()) {
            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = errorMessage,
                color = Color(0xFFB44E78),
                fontSize = 12.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = {
                    currentSlide =
                        if (currentSlide == 0) {
                            images.size - 1
                        } else {
                            currentSlide - 1
                        }
                },
                modifier = Modifier.weight(1f).height(48.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = DarkLavender
                )
            ) {
                Text(
                    text = "♡ Previous",
                    fontSize = 13.sp
                )
            }

            Button(
                onClick = {
                    currentSlide =
                        (currentSlide + 1) % images.size
                },
                modifier = Modifier.weight(1f).height(48.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = DarkLavender
                )
            ) {
                Text(
                    text = "Next ♡",
                    fontSize = 13.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "✧ ────── ୨୧ ────── ✧",
            fontSize = 14.sp,
            color = RosePink
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SlideshowAppPreview() {
    GalleryTheme {
        SlideshowApp()
    }
}

@Preview(showBackground = true)
@Composable
fun SlideshowScreenPreview() {
    GalleryTheme {
        Surface(color = SoftPink) {
            SlideshowScreen(onBack = {})
        }
    }
}
