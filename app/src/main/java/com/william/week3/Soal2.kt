package com.william.week3

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.william.week3.ui.theme.Week3Theme
import kotlinx.coroutines.delay
import kotlinx.coroutines.time.delay
import kotlin.math.roundToInt

@Composable
fun Soal2() {
    var coin by rememberSaveable { mutableIntStateOf(0) }
    var coinPerTap by rememberSaveable { mutableIntStateOf(1) }
    var coinToNextUpgrade by rememberSaveable { mutableIntStateOf(10) }
    var nextCoinPerTap by rememberSaveable {mutableIntStateOf(2) }
    var isClicked by rememberSaveable() { mutableStateOf(false) }

    Box(modifier = Modifier
        .fillMaxSize()){
        Image(painter = painterResource(R.drawable.bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.3f))
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                Modifier
                    .size(150.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White.copy(alpha = 0.5f)),
                contentAlignment = Alignment.Center
            ){
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ){
                    Text(
                        text = "Your Coins",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = Color.White
                    )
                    Text(
                        text = "$coin",
                        fontWeight = FontWeight.Bold,
                        fontSize = 36.sp,
                        color = Color.Green
                    )
                    Text(
                        text = "$coinPerTap per Tap",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }

            }
            Box(){
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "TAP THE CAT!",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.White
                    )
                    Image(
                        painter = painterResource(
                            if (isClicked) R.drawable.kucing_buka
                            else R.drawable.kucing
                        ),
                        contentDescription = null,
                        modifier = Modifier.size(200.dp)
                            .clip(RoundedCornerShape(5))
                            .pointerInput(Unit) {
                                awaitEachGesture {
                                    awaitFirstDown()
                                    coin += coinPerTap
                                    isClicked = true

                                    waitForUpOrCancellation()
                                    isClicked = false
                                }
                            }
                    )
                    Text("Purr~~", color = Color.Gray)
                }
            }
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)
                    .fillMaxWidth()
                    .height(150.dp)

            ){
                Column(

                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize().padding(20.dp)
                ) {
                    Text(
                        text = "Give Me Your Coin",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.Black
                    )
                    Text(
                        text = "Next Upgrade +$nextCoinPerTap coins per tap",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color(0xFFFFA7A9A8))
                    Button(
                        onClick = {
                            if (coin >= coinToNextUpgrade) {
                                coin -= coinToNextUpgrade
                                coinPerTap += nextCoinPerTap
                                nextCoinPerTap = (coinPerTap * 1.5).roundToInt()
                                coinToNextUpgrade *= 2
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (coin >= coinToNextUpgrade)
                                Color.Green
                            else
                                Color.Gray,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.height(100.dp).fillMaxWidth()
                    ) {
                        Text("Find $coinToNextUpgrade More Coins")
                    }
                }
            }
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Soal2Preview() {
    Soal2()
}