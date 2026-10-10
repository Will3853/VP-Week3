package com.william.week3

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SoalBonus2() {
    var spot by rememberSaveable { mutableStateOf("") }
    var pengalaman by rememberSaveable { mutableStateOf("") }
    var catatan by rememberSaveable { mutableStateOf("") }
    var kepuasan by rememberSaveable { mutableStateOf("Luar Biasa ★") }

    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121728))
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        Brush.horizontalGradient(
                            listOf(
                                Color(0xFF354B91),
                                Color(0xFF568AC8)
                            )
                        )
                    )
                    .padding(14.dp)
            ) {
                Text(
                    "LOG PERJALANAN",
                    color = Color(0xFFB9D6FF),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                     "Tromsø, Norway ❄",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    "Ekspedisi Perjalanan",
                    color = Color(0xFFD6E5FF),
                    fontSize = 12.sp
                )
            }

            Column(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Color(0xFF353C4C),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFF242B3B))
            ) {
                Text(
                    "SPOT WISATA FAVORIT",
                    color = Color(0xFFADB7CB),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 10.dp, start = 16.dp)
                )

                TextField(
                    value = spot,
                    onValueChange = { spot = it },
                    placeholder = { Text("Masukkan nama tempat wisata") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFF242B3B),
                        unfocusedContainerColor = Color(0xFF242B3B),
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedIndicatorColor = Color(0xFF242B3B),
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
            }

            Column(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Color(0xFF353C4C),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFF242B3B))
            ) {
                Text(
                    "APA YANG PALING KAMU NIKMATI?",
                    color = Color(0xFFADB7CB),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 10.dp, start = 16.dp)
                )

                TextField(
                    value = pengalaman,
                    onValueChange = { pengalaman = it },
                    placeholder = {
                        Text("Ceritakan pengalaman perjalananmu...")
                    },
                    minLines = 3,
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFF242B3B),
                        unfocusedContainerColor = Color(0xFF242B3B),
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedIndicatorColor = Color(0xFF242B3B),
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
            }
            Column(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Color(0xFF353C4C),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFF242B3B))
            ) {

                Text(
                    "CATATAN TAMBAHAN / PERLENGKAPAN",
                    color = Color(0xFFADB7CB),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 10.dp, start = 16.dp)
                )

                OutlinedTextField(
                    value = catatan,
                    onValueChange = { catatan = it },
                    placeholder = {
                        Text("Ketik perlengkapan ekstra di sini...")
                    },
                    minLines = 2,
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFF242B3B),
                        unfocusedContainerColor = Color(0xFF242B3B),
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedIndicatorColor = Color(0xFF242B3B),
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
            }

            Text(
                "TINGKAT KEPUASAN:",
                color = Color(0xFFADB7CB),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                listOf("Biasa", "Seru", "Luar Biasa ★").forEach {
                    OutlinedButton(
                        onClick = { kepuasan = it },
                        shape = RoundedCornerShape(50),
                        contentPadding = PaddingValues(
                            horizontal = 10.dp,
                            vertical = 0.dp
                        ),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = if (kepuasan == it)
                                Color(0xFF82BDF4)
                            else Color(0xFF121728),
                            contentColor = if (kepuasan == it)
                                Color(0xFF121728)
                            else Color(0xFFADB7CB)
                        ),
                        border = BorderStroke(1.dp, Color(0xFF353C4C))
                    ) {
                        Text(it, fontSize = 12.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(
                "Status: Draft Tersimpan",
                color = Color(0xFF82BDF4),
                fontSize = 12.sp
            )
        }

        FloatingActionButton(
            onClick = {
                Toast.makeText(
                    context,
                    "Jurnal ${spot.ifBlank { "Tanpa Nama" }} berhasil disimpan!",
                    Toast.LENGTH_SHORT
                ).show()
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            containerColor = Color(0xFF82BDF4),
            contentColor = Color(0xFF121728)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Simpan jurnal"
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SoalBonus2Preview() {
    SoalBonus2()
}