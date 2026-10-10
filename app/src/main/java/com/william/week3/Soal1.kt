package com.william.week3

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DirectionsRun
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.william.week3.ui.theme.Week3Theme
import kotlinx.coroutines.delay
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Week3Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ScreenManager(
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}
enum class Screen {
    START,
    WAIT,
    GO,
    TRIAL,
    RESULT,
    FAIL
}
@Composable
fun ScreenManager(modifier: Modifier = Modifier) {
    var startTime by rememberSaveable {
        mutableLongStateOf(0L)
    }

    var trial by rememberSaveable {
        mutableIntStateOf(1)
    }

    val reactionTimes = rememberSaveable {
        mutableStateListOf<Long>()
    }

    var currentScreen by rememberSaveable {
        mutableStateOf(Screen.START)
    }

    when (currentScreen) {

        Screen.START -> {
            Halaman1(
                onClick = {
                    currentScreen = Screen.WAIT
                }
            )
        }

        Screen.WAIT -> {
            LaunchedEffect(Unit) {
                val randomDelay = Random.nextLong(500L, 4500L)
                delay(randomDelay)
                currentScreen = Screen.GO
            }
            Halaman2(
                onClick = {
                    currentScreen = Screen.FAIL
                }
            )
        }

        Screen.GO -> {
            LaunchedEffect(currentScreen) {
                startTime = System.currentTimeMillis()
            }
            Halaman3(
                onClick = {
                    val reactionTime = System.currentTimeMillis() - startTime
                    reactionTimes.add(reactionTime)
                    currentScreen = Screen.TRIAL
                }
            )
        }

        Screen.TRIAL ->{
            Halaman4(
                trial = trial,
                reactionTime = reactionTimes.last(),
                reactionTimes = reactionTimes,
                onContinue = {
                    if (trial < 3) {
                        trial++
                        currentScreen = Screen.WAIT
                    } else {
                        currentScreen = Screen.RESULT
                    }
                }
            )
        }
        Screen.RESULT -> {
            val averageTime = reactionTimes.average().toLong()

            Halaman5(
                averageTime = averageTime,
                onRestart = {
                    trial = 1
                    reactionTimes.clear()
                    currentScreen = Screen.START
                }
            )
        }
        Screen.FAIL -> {
            Fail(
                onClick = {
                    currentScreen = Screen.START
                }
            )
        }
    }
}
@Composable
fun Halaman1(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF90CCDB))
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center

    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Reaction", fontSize = 25.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Spacer(
                modifier = Modifier.padding(top=16.dp)
            )
            Icon(
                imageVector = Icons.Default.Bolt,
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier.size(200.dp)
            )
            Spacer(
                modifier = Modifier.padding(top=16.dp)
            )
            Text(text = "Test", fontSize = 18.sp,fontWeight = FontWeight.Bold, color = Color.White)
            Spacer(
                modifier = Modifier.padding(top=16.dp)
            )
            Text(text = "Click To Start", fontSize = 15.sp, color = Color.White)

        }

    }
}

@Composable
fun Halaman2(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFD0D7FF))
            .clickable {
                onClick()
                       },
        contentAlignment = Alignment.Center

    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Get Ready", fontSize = 25.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Spacer(
                modifier = Modifier.padding(top=16.dp)
            )
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier.size(200.dp)
            )
            Spacer(
                modifier = Modifier.padding(top=16.dp)
            )
            Text(text = "Wait For Green Light...", fontSize = 18.sp,fontWeight = FontWeight.Bold, color = Color.White)
            Spacer(
                modifier = Modifier.padding(top=16.dp)
            )
            Text(text = "Dont Click Yet!", fontSize = 15.sp, color = Color.White)

        }

    }
}
@Composable
fun Halaman3(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF6DAC5C))
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center

    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "GO!", fontSize = 25.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Spacer(
                modifier = Modifier.padding(top=16.dp)
            )
            Icon(
                imageVector = Icons.Default.DirectionsRun,
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier.size(200.dp)
            )
            Spacer(
                modifier = Modifier.padding(top=16.dp)
            )
            Text(text = "CLICK NOW!", fontSize = 18.sp,fontWeight = FontWeight.Bold, color = Color.White)
            Spacer(
                modifier = Modifier.padding(top=16.dp)
            )
            Text(text = "TAP AS FAST AS YOU CAN!", fontSize = 15.sp, color = Color.White)

        }

    }
}
@Composable
fun Halaman4(
    trial: Int,
    reactionTime: Long,
    reactionTimes: List<Long>,
    onContinue: () -> Unit
){

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF6DAC5C))
            .clickable {
                onContinue()
            },
        contentAlignment = Alignment.Center

    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Trial $trial Complete!", fontSize = 25.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Spacer(
                modifier = Modifier.padding(top=16.dp)
            )
            Box(Modifier
                .size(150.dp)
                .clip(RoundedCornerShape(100.dp))
                .background(Color.White)
            ){
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "",
                    tint = Color(0xFF6DAC5C),
                    modifier = Modifier.size(200.dp)
                )
            }
            Spacer(
                modifier = Modifier.padding(top=16.dp)
            )
            Text(text = "Time: $reactionTime ms", fontSize = 18.sp,fontWeight = FontWeight.Bold, color = Color.White)
            Spacer(
                modifier = Modifier.padding(top=16.dp)
            )
            Text(text = "Continue to trial ${trial + 1}", fontSize = 15.sp, color = Color.White)
            Box(Modifier
                .shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(16.dp)
                )
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(16.dp)
            ){
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Trial Result",
                        fontWeight = FontWeight.Bold,
                        color = Color.Blue
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("1")
                            Text("${reactionTimes.getOrNull(0) ?: "-"} ms")
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("2")
                            Text("${reactionTimes.getOrNull(1) ?: "-"} ms")
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("3")
                            Text("${reactionTimes.getOrNull(2) ?: "-"} ms")
                        }
                    }


            }
        }

    }
}

    }
@Composable
fun Halaman5(
    averageTime: Long,
    onRestart: () -> Unit
) {
    val message: String
    val image: Int
    val backgroundColor: Color

    if (averageTime < 180) {
        message = "DANG YOU ARE SO FAST BRO!"
        image = R.drawable.salam
        backgroundColor = Color(0xFF75E181)
    } else if (averageTime < 280) {
        message = "YOUR REFLEX IS GOOD"
        image = R.drawable.jempol
        backgroundColor = Color(0xFF5796EB)
    } else if (averageTime < 450) {
        message = "MEH LIKE OTHER PERSON"
        image = R.drawable.standar
        backgroundColor = Color(0xFFEDA037)
    } else {
        message = "YOU LIKE A SNAIL BRO"
        image = R.drawable.siput
        backgroundColor = Color(0xFFE65D35)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .clickable { onRestart() },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = message,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Image(
                painter = painterResource(id = image),
                contentDescription = "Reaction result",
                modifier = Modifier.size(180.dp)
            )

            Text(
                text = "Average: ${averageTime}ms",
                color = Color.White,
                fontSize = 18.sp
            )

            Text(
                text = "Click to Start New Test",
                color = Color.White,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun Fail(onClick: () -> Unit){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE65D35))
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center

    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "FAIL!", fontSize = 25.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Spacer(
                modifier = Modifier.padding(top=16.dp)
            )
            Icon(
                imageVector = Icons.Default.ThumbDown,
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier.size(200.dp)
            )
            Spacer(
                modifier = Modifier.padding(top=16.dp)
            )
            Text(text = "You click too early.", fontSize = 18.sp,fontWeight = FontWeight.Bold, color = Color.White)
            Spacer(
                modifier = Modifier.padding(top=16.dp)
            )
            Text(text = "TRY AGAIN", fontSize = 15.sp, color = Color.White)

        }

    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Soal1Preview() {
    Week3Theme {
        Halaman1(
            onClick = {}
        )
    }
}