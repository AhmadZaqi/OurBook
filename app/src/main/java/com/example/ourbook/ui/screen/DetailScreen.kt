package com.example.ourbook.ui.screen

import android.graphics.BitmapFactory
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ourbook.R
import com.example.ourbook.ui.navigation.Screen
import com.example.ourbook.ui.theme.OurBookTheme
import com.example.ourbook.ui.viewmodel.RepositoryViewModel

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    onBack: ()-> Unit,
    id: Int,
    viewModel: RepositoryViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val context = LocalContext.current
    val detail by viewModel.selectedData.collectAsState()
    val scrollState = rememberScrollState()
    LaunchedEffect(key1 = true) {
        viewModel.findData(context = context, id = id)
    }
    Column(
        modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        ) {
            Column(Modifier.padding(8.dp)) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    IconButton(
                        onClick = onBack,
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .size(30.dp)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
                            contentDescription = "Back"
                        )
                    }
                    Text(
                        text = "Detail Information",
                        fontWeight = FontWeight.Black,
                        fontSize = 18.sp,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                detail?.let { data ->
                    val image = BitmapFactory.decodeByteArray(data.photo, 0, data.photo.size)
                    Image(
                        bitmap = image.asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .size(160.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Nama: ${data.nama}",
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "Nama Panggilan: ${data.namaPanggilan}",
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "Email: ${data.email}",
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "No. HP: ${data.noHP}",
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "Tanggal Lahir: ${data.tglLahir}",
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "Alamat: ${data.alamat}",
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterHorizontally),
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
}

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    OurBookTheme {
        DetailScreen(onBack = { /*TODO*/ }, id = -1)
    }
}