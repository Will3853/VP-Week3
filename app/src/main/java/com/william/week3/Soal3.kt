package com.william.week3

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.random.Random

enum class Halaman {
    START, COUNTDOWN, RUNNING, GAME_OVER
}

enum class Mode {
    COLOR, TEXT
}

enum class Choice {
    INK, WORD
}

enum class ColorName(val color: Color) {
    RED(Color.Red),
    BLUE(Color.Blue),
    GREEN(Color.Green),
    YELLOW(Color.Yellow)
}

@Composable
fun Soal3() {
    var halaman by rememberSaveable { mutableStateOf(Halaman.START) }
    var mode by rememberSaveable { mutableStateOf(Mode.COLOR) }
    var word by rememberSaveable { mutableStateOf(ColorName.RED) }
    var ink by rememberSaveable { mutableStateOf(ColorName.BLUE) }
    var score by rememberSaveable { mutableIntStateOf(0) }
    var strikes by rememberSaveable { mutableIntStateOf(0) }
    var timeLeftMs by rememberSaveable { mutableIntStateOf(5000) }
    var countdownText by rememberSaveable { mutableStateOf("3") }
    var isInkLeft by rememberSaveable { mutableStateOf(true) }
    var questionId by rememberSaveable { mutableIntStateOf(0) }
    var bestScore by rememberSaveable { mutableIntStateOf(0) }

    fun nextQuestion() {
        word = ColorName.values().random()

        do {
            ink = ColorName.values().random()
        } while (ink == word)

        mode = Mode.values().random()
        isInkLeft = Random.nextBoolean()

        timeLeftMs = 5000
        questionId++
    }

    fun startGame() {
        score = 0
        strikes = 0
        timeLeftMs = 5000
        nextQuestion()
        halaman = Halaman.COUNTDOWN
    }

    fun exit() {
        score = 0
        strikes = 0
        timeLeftMs = 5000
        nextQuestion()
        halaman = Halaman.START
    }

    fun answer(choice: Choice) {
        if (halaman != Halaman.RUNNING) return

        val correct = when (mode) {
            Mode.COLOR -> choice == Choice.INK
            Mode.TEXT -> choice == Choice.WORD
        }

        if (correct) {
            score++
            if (score > bestScore) {
                bestScore = score
            }
        } else {
            strikes++

            if (strikes >= 3) {
                halaman = Halaman.GAME_OVER
                return
            }
        }

        nextQuestion()
    }

    LaunchedEffect(halaman) {
        if (halaman == Halaman.COUNTDOWN) {
            countdownText = "3"
            delay(700)

            countdownText = "2"
            delay(700)

            countdownText = "1"
            delay(700)

            countdownText = "Start!"
            delay(700)

            halaman = Halaman.RUNNING
        }
    }

    LaunchedEffect(halaman, questionId) {
        if (halaman == Halaman.RUNNING) {
            val currentQuestion = questionId
            var remaining = 5000

            while (remaining > 0) {
                delay(100)
                remaining -= 100

                if (
                    halaman != Halaman.RUNNING ||
                    questionId != currentQuestion
                ) {
                    break
                }

                timeLeftMs = remaining
            }

            if (
                remaining <= 0 &&
                halaman == Halaman.RUNNING &&
                questionId == currentQuestion
            ) {
                strikes++

                if (strikes >= 3) {
                    halaman = Halaman.GAME_OVER
                } else {
                    nextQuestion()
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        when (halaman) {

            Halaman.START -> {
                Text(
                    text = "Welcome \n" +
                            "to \n" + "Color Word Matching",
                    fontSize = 28.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(24.dp))

                Button(onClick = { startGame() },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)) {
                    Text("Start Game", color = Color.Black)
                }
            }

            Halaman.COUNTDOWN -> {
                Text(
                    text = countdownText,
                    fontSize = 36.sp,
                )
            }

            Halaman.RUNNING -> {

                Column(Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(200.dp)) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Mode: $mode",
                            fontSize = 16.sp
                        )

                        Box(){

                            Row (horizontalArrangement = Arrangement.spacedBy(4.dp)){
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = "",
                                    tint = Color.Green
                                )
                                Text(": $score")
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "",
                                    tint = Color.Red
                                )
                                Text(": $strikes / 3")
                            }
                        }
                    }
                    Box(){
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "${(timeLeftMs + 999) / 1000} s",
                                fontSize = 24.sp
                            )

                            Text(
                                text = word.name,
                                color = ink.color,
                                fontSize = 52.sp
                            )
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        if (isInkLeft) {
                            Button(
                                onClick = { answer(Choice.INK) },
                                modifier = Modifier.weight(1f).padding(10.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
                            ){
                                Text(ink.name)
                            }

                            Button(
                                onClick = { answer(Choice.WORD) },
                                modifier = Modifier.weight(1f).padding(10.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
                            ){
                                Text(word.name)
                            }
                        } else {
                            Button(
                                onClick = { answer(Choice.WORD) },
                                modifier = Modifier.weight(1f).padding(10.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
                            ){
                                Text(word.name)
                            }

                            Button(
                                onClick = { answer(Choice.INK) },
                                modifier = Modifier.weight(1f).padding(10.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
                            ){
                                Text(ink.name)
                            }
                        }
                    }
                }
            }

            Halaman.GAME_OVER -> {
                Column(Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {
                    Text(
                        text = "Game Over!",
                        fontSize = 36.sp,
                    )

                    Spacer(Modifier.height(20.dp))

                    Text("You're Score\n $score", fontSize = 24.sp, textAlign = TextAlign.Center)
                    Text("Best Score\n $bestScore", fontSize = 16.sp, textAlign = TextAlign.Center)

                    Spacer(Modifier.height(24.dp))

                    Button(onClick = { startGame() },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
                    ) {
                        Text("Restart Game")
                    }
                    Button(onClick = { exit() },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
                    ) {
                        Text("Exit")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Soal3Preview() {
    Soal3()
}