package com.william.week3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

enum class Rps(val emoji: String, val label: String) {
    ROCK("✊", "Rock"),
    PAPER("✋", "Paper"),
    SCISSOR("✌️", "Scissor")
}

enum class State {
    START, PICK, RESULT, FINISHED
}

enum class Result {
    WIN, LOSE, DRAW
}

@Composable
fun Soal4() {
    var page by rememberSaveable { mutableStateOf(State.START) }
    val bestOf by rememberSaveable { mutableIntStateOf(5) }
    var yourScore by rememberSaveable { mutableIntStateOf(0) }
    var cpuScore by rememberSaveable { mutableIntStateOf(0) }
    var bestScore by rememberSaveable { mutableIntStateOf(0) }
    var playerChoice by rememberSaveable { mutableStateOf<Rps?>(null) }
    var cpuChoice by rememberSaveable { mutableStateOf<Rps?>(null) }
    var roundResult by rememberSaveable { mutableStateOf<Result?>(null) }
    var buttonOrder by remember { mutableStateOf(Rps.entries.shuffled()) }
    val targetScore = bestOf / 2 + 1

    fun resetRound() {
        playerChoice = null
        cpuChoice = null
        roundResult = null
        buttonOrder = Rps.entries.shuffled()
    }

    fun startGame() {
        yourScore = 0
        cpuScore = 0
        resetRound()
        page = State.PICK
    }

    fun chooseMove(choice: Rps) {
        if (page != State.PICK) return
        playerChoice = choice
        cpuChoice = Rps.entries.random()
        roundResult = when {
            choice == cpuChoice -> Result.DRAW
            choice == Rps.ROCK && cpuChoice == Rps.SCISSOR -> Result.WIN
            choice == Rps.PAPER && cpuChoice == Rps.ROCK -> Result.WIN
            choice == Rps.SCISSOR && cpuChoice == Rps.PAPER -> Result.WIN
            else -> Result.LOSE
        }
        when (roundResult) {
            Result.WIN -> yourScore++
            Result.LOSE -> cpuScore++
            Result.DRAW -> {}
            else -> {}
        }
        val currentDifference = yourScore - cpuScore
        if (currentDifference > bestScore) {
            bestScore = currentDifference
        }
        page = State.RESULT
    }

    LaunchedEffect(page) {
        if (page == State.RESULT) {
            delay(700)
            if (yourScore >= targetScore ||
                cpuScore >= targetScore
            ) {
                page = State.FINISHED
            } else {
                resetRound()
                page = State.PICK
            }
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF3F5FA)
    ) {
            when (page) {

                State.START -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Row(modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween){
                            Text("🧑" +" $yourScore - $cpuScore "+ "🤖", fontSize = 20.sp)
                            Text("Best of $bestOf", fontSize = 20.sp)
                        }
                        Spacer(Modifier.height(300.dp))
                        Text(
                            text = "ROCK ● PAPER ● SCISSORS",
                            fontSize = 20.sp,
                        )
                        Spacer(Modifier.height(36.dp))

                        Button(
                            onClick = { startGame() },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
                            modifier = Modifier
                                .width(200.dp)
                                .height(56.dp)

                        ) {
                            Text("Start", fontSize = 18.sp)
                        }
                    }
                }

                State.PICK, State.RESULT -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Row(modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween){
                            Text("🧑" +" $yourScore - $cpuScore "+ "🤖", fontSize = 20.sp)
                            Text("Best of $bestOf", fontSize = 20.sp)
                        }
                        Spacer(Modifier.height(300.dp))


                        if (page == State.PICK) {
                            Text(
                                text = "Pick your move!",
                                fontSize = 22.sp,
                            )

                            Spacer(Modifier.height(20.dp))

                            Text(
                                text = "❔       VS       ❔",
                                fontSize = 36.sp
                            )

                            Spacer(Modifier.height(32.dp))

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceAround,
                                modifier = Modifier.fillMaxWidth()
                                ) {
                                Button(
                                    onClick = { chooseMove(Rps.ROCK) },
                                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
                                    modifier = Modifier
                                        .height(64.dp)
                                        .padding(vertical = 4.dp)

                                ) {
                                    Text(
                                        text = "${Rps.ROCK.emoji}  ${Rps.ROCK.label}",
                                        fontSize = 12.sp
                                    )
                                }

                                Button(
                                    onClick = { chooseMove(Rps.PAPER) },
                                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
                                    modifier = Modifier
                                        .height(64.dp)
                                        .padding(vertical = 4.dp)

                                ) {
                                    Text(
                                        text = "${Rps.PAPER.emoji}  ${Rps.PAPER.label}",
                                        fontSize = 12.sp
                                    )
                                }

                                Button(
                                    onClick = { chooseMove(Rps.SCISSOR) },
                                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
                                    modifier = Modifier
                                        .height(64.dp)
                                        .padding(vertical = 4.dp)

                                ) {
                                    Text(
                                        text = "${Rps.SCISSOR.emoji}  ${Rps.SCISSOR.label}",
                                        fontSize = 12.sp
                                    )
                                }
                            }
                        } else {
                            val player = playerChoice
                            val computer = cpuChoice
                            val result = roundResult


                            Spacer(Modifier.height(24.dp))

                            Text(
                                text = "${player?.emoji}  VS  ${computer?.emoji}",
                                fontSize = 42.sp,
                            )

                            Spacer(Modifier.height(16.dp))

                            Text(
                                text = when (result) {
                                    Result.WIN -> "You Win!"
                                    Result.LOSE -> "You Lose"
                                    Result.DRAW -> "Draw"
                                    else -> ""
                                },
                                fontSize = 30.sp,
                            )
                        }
                    }
                }

                State.FINISHED -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        val playerWon = yourScore >= targetScore
                        Row(modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween){
                            Text("🧑" +" $yourScore - $cpuScore "+ "🤖", fontSize = 20.sp)
                            Text("Best of $bestOf", fontSize = 20.sp)
                        }
                        Spacer(Modifier.height(300.dp))
                        Text(
                            text = if (playerWon)
                                "You Win the Match!"
                            else
                                "You Lose the Match",
                            fontSize = 28.sp
                        )

                        Spacer(Modifier.height(24.dp))

                        Text(
                            text = "Best Score: $bestScore",
                            fontSize = 18.sp
                        )

                        Spacer(Modifier.height(32.dp))

                        Row(
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            Button(
                                onClick = { startGame() },
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
                                modifier = Modifier
                                    .height(56.dp)
                            ) {
                                Text("Restart")
                            }


                            Button(
                                onClick = {
                                    resetRound()
                                    yourScore = 0
                                    cpuScore = 0
                                    page = State.START
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
                                modifier = Modifier
                                    .height(56.dp)
                            ) {
                                Text("Exit")
                            }
                        }
                    }

                }
            }
        }
    }

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Soal4Preview() {
    Soal4()
}