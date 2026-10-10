package com.william.week3

import android.R
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalCafe
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.paint
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class Size(){
    REGULAR,
    LARGE
}

@Composable
fun SoalBonus1(){
    var currentSize by rememberSaveable { mutableStateOf(Size.REGULAR) }
    var regularAmount by rememberSaveable { mutableIntStateOf(0) }
    var largeAmount by rememberSaveable { mutableIntStateOf(0) }
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFBF8F6))
    ){
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        )
        {
            Row(Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            )
            {
                Text("Kopi Kenangan Senja", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text("☕", fontSize = 20.sp)
            }
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = BorderStroke(1.dp, Color(0xFFF0DCB9))
            ) {
                Column(modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .fillMaxWidth()
                            .height(180.dp)
                            .background(Color(0xFF6D3D22)),
                        contentAlignment = Alignment.Center
                    ){
                        Icon(
                            imageVector = Icons.Filled.LocalCafe,
                            contentDescription = null,
                            tint = Color(0xFFF0DCB9),
                            modifier = Modifier.size(60.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text("Caramel Latte", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text("Espresso shot, Steamed milk & caramel syrup", color = Color(0xFF6D3D22))
                    Spacer(modifier = Modifier.height(10.dp))
                    Text("Rp 25.000 / cup", color = Color(0xFFC46B3D), fontWeight = FontWeight.Bold, fontSize = 20.sp)
                }
            }

            Column {
                Text(
                    "PILIHAN UKURAN:",
                    color = Color(0xFF6D3D22),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    OutlinedButton(
                        onClick = {
                            currentSize = Size.REGULAR
                        },
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.weight(5f),
                        border = when (currentSize){
                            Size.REGULAR -> {BorderStroke(2.dp, Color(0xFFC46B3D))}
                            Size.LARGE -> {BorderStroke(1.dp, Color(0xFFF0DCB9))}
                        },
                        colors = when (currentSize){
                            Size.REGULAR -> {ButtonDefaults.buttonColors(containerColor = Color.White)}
                            Size.LARGE -> {ButtonDefaults.buttonColors(containerColor = Color(0xFFFBF8F6))}
                        }
                    )
                    {
                        Text("Regular (+0k)", fontSize = 16.sp, color = when (currentSize) {
                            Size.REGULAR -> {Color(0xFFC46B3D)}
                            Size.LARGE -> {Color(0xFF6D3D22)}
                        },
                            fontWeight =  when (currentSize) {
                                Size.REGULAR -> { FontWeight.Bold}
                                Size.LARGE -> {FontWeight.Normal}
                            }
                        )
                    }
                    OutlinedButton(
                        onClick = {
                            currentSize = Size.LARGE
                        },
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.weight(5f),
                        border = when (currentSize){
                            Size.LARGE -> {BorderStroke(2.dp, Color(0xFFC46B3D))}
                            Size.REGULAR -> {BorderStroke(1.dp, Color(0xFFF0DCB9))}
                        },
                        colors = when (currentSize){
                            Size.LARGE -> {ButtonDefaults.buttonColors(containerColor = Color.White)}
                            Size.REGULAR -> {ButtonDefaults.buttonColors(containerColor = Color(0xFFFBF8F6))}
                        }
                    )
                    {
                        Text("Large (+6K)", fontSize = 16.sp, color = when (currentSize) {
                            Size.LARGE -> {Color(0xFFC46B3D)}
                            Size.REGULAR -> {Color(0xFF6D3D22)}
                        },
                            fontWeight =  when (currentSize) {
                                Size.LARGE -> { FontWeight.Bold}
                                Size.REGULAR -> {FontWeight.Normal}
                            })
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .border(
                            width = 1.dp,
                            color = Color(0xFFF0DCB9),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(16.dp)){
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Jumlah Pesanan: ", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            OutlinedButton(
                                onClick = {
                                    when (currentSize) {
                                        Size.REGULAR -> if (regularAmount != 0){regularAmount--}
                                        Size.LARGE -> if (largeAmount != 0){largeAmount--}
                                    }
                                },
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier.size(42.dp),
                                contentPadding = PaddingValues(0.dp)
                            ) {
                                Text("-", fontSize = 18.sp)
                            }

                            Text(
                                text = when (currentSize) {
                                    Size.REGULAR -> regularAmount.toString()
                                    Size.LARGE -> largeAmount.toString()
                                },
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )

                            OutlinedButton(
                                onClick = {
                                    when (currentSize) {
                                        Size.REGULAR -> regularAmount++
                                        Size.LARGE -> largeAmount++
                                    }
                                },
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier.size(42.dp),
                                contentPadding = PaddingValues(0.dp)
                            ) {
                                Text("+", fontSize = 18.sp)
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .drawBehind {
                            drawRoundRect(
                                color = Color(0xFFF0DCB9),
                                style = Stroke(
                                    width = 2.dp.toPx(),
                                    pathEffect = PathEffect.dashPathEffect(
                                        floatArrayOf(5.dp.toPx(), 5.dp.toPx())
                                    )
                                ),
                                cornerRadius = CornerRadius(10.dp.toPx())
                            )
                        }
                        .padding(16.dp)
                ) {
                    var subtotal = 25000 * regularAmount + 31000 * largeAmount
                    var pajak = (subtotal * 0.1).toInt()
                    var totalAkhir = subtotal + pajak
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween

                        ) {
                            Text("Subtotal:", color = Color(0xFF6D3D22))
                            Text("Rp ${subtotal}", color = Color(0xFF6D3D22))
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween

                        ) {
                            Text("Pajak Resto (10%):", color = Color(0xFF6D3D22))
                            Text("Rp ${pajak}", color = Color(0xFF6D3D22))
                        }
                        Spacer(Modifier.padding(2.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(Color(0xFFF0DCB9))
                        )
                        Spacer(Modifier.padding(2.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Total Tagihan", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                            Text("Rp ${totalAkhir}", color = Color(0xFFC46B3D), fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        }
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                Button(onClick = {
                    Toast.makeText(
                        context,
                        "Order added!",
                        Toast.LENGTH_SHORT
                    ).show()
                    regularAmount = 0
                    largeAmount = 0
                },
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC4693A))
                ) {
                    Text("TAMBAH KE KERANJANG", fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(10.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SoalBonus1Preview() {
    SoalBonus1()
}