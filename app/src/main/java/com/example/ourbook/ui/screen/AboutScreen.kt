package com.example.ourbook.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ourbook.R
import com.example.ourbook.ui.theme.OurBookTheme

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier,
    onBack: ()-> Unit
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier.fillMaxSize().verticalScroll(scrollState)
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
        ){
            IconButton(
                onClick = onBack,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 16.dp)
                    .size(30.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
                    contentDescription = "Back"
                )
            }
            Row(
                modifier = Modifier
                    .align(Alignment.Center),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = null,
                    Modifier.size(70.dp),
                    contentScale = ContentScale.Crop,
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
                )
                Text(
                    text = "OurBook",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ){
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .align(Alignment.BottomCenter)
                    .shadow(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 16.dp
                )
            ){}
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.me),
                    contentDescription = null,
                    modifier = Modifier
                        .size(160.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Ahmad Zaqi",
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Black,
                    fontSize = 20.sp
                )
                Text(
                    text = "ahmadzaqi490@gmail.com",
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 16.sp
                )
                Text(
                    text = "XI RPL 2 || Absen 3",
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
        Box(modifier = Modifier
            .weight(1f)
            .fillMaxSize()){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "October 17 2024")
                Text(
                    text = "V1.0.0",
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    OurBookTheme {
        AboutScreen {

        }
    }
}