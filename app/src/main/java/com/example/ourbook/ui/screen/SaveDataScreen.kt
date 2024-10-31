package com.example.ourbook.ui.screen

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ourbook.R
import com.example.ourbook.model.DataUser
import com.example.ourbook.ui.components.CustomButton
import com.example.ourbook.ui.components.CustomTextBox
import com.example.ourbook.ui.components.DatePickerDocked
import com.example.ourbook.ui.components.DatePickerTextField
import com.example.ourbook.ui.components.DialogBox
import com.example.ourbook.ui.theme.OurBookTheme
import com.example.ourbook.ui.viewmodel.RepositoryViewModel
import java.io.ByteArrayOutputStream

@Composable
fun SaveDataScreen(
    modifier: Modifier = Modifier,
    viewModel: RepositoryViewModel = viewModel(),
    onBack: () -> Unit,
    existingDataUserID: Int
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val existingDataUser by viewModel.selectedData.collectAsState()
        val context = LocalContext.current
        var showImagePicker by remember {
            mutableStateOf(false)
        }
        var showConfirmation by remember {
            mutableStateOf(false)
        }
        var showDatePicker by remember {
            mutableStateOf(false)
        }

        var nama by remember {
            mutableStateOf("")
        }
        var namaPanggilan by remember {
            mutableStateOf("")
        }
        var email by remember {
            mutableStateOf("")
        }
        var alamat by remember {
            mutableStateOf("")
        }
        var tglLahir by remember {
            mutableStateOf("")
        }
        var noHP by remember {
            mutableStateOf("")
        }
        var photo by remember {
            mutableStateOf<ByteArray?>(null)
        }
        var title by remember {
            mutableStateOf("Add New Data")
        }

        if (existingDataUserID != -1) {
            viewModel.findData(context, existingDataUserID)
            existingDataUser?.let {
                LaunchedEffect(key1 = true) {
                    nama = it.nama
                    namaPanggilan = it.namaPanggilan
                    alamat = it.alamat ?: ""
                    email = it.email
                    tglLahir = it.tglLahir
                    noHP = it.noHP
                    photo = it.photo
                    title = "Edit Data"
                }
            }
        }

        val galleryLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) {
            it?.let {
                val stream = ByteArrayOutputStream()
                val bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, it)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream)
                photo = stream.toByteArray()
            }
        }
        val cameraLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.TakePicturePreview()
        ) {
            it?.let {
                val stream = ByteArrayOutputStream()
                it.compress(Bitmap.CompressFormat.JPEG, 70, stream)
                photo = stream.toByteArray()
            }
        }

        if (showImagePicker){
            DialogPickImage(
                onCameraSelected = {
                    cameraLauncher.launch(null)
                    showImagePicker = false
                },
                onGallerySelected = {
                    galleryLauncher.launch("image/*")
                    showImagePicker = false
                },
                onDismissRequest = { showImagePicker = false }
            )
        }

        if (showConfirmation){
            DialogBox(
                onDismissRequest = { showConfirmation = false },
                onConfirmation = {
                    val newData = DataUser(
                        id = 0,
                        nama = nama,
                        namaPanggilan = namaPanggilan,
                        email = email,
                        photo = photo!!,
                        noHP = noHP,
                        alamat = alamat,
                        tglLahir = tglLahir
                    )
                    if (existingDataUserID != -1){
                        viewModel.editData(context, newData.copy(id = existingDataUserID))
                        Toast.makeText(context, "Data updated successfully", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        viewModel.insertNewData(context, newData)
                        Toast.makeText(context, "Data added successfully", Toast.LENGTH_SHORT).show()
                    }
                    showConfirmation = false
                    onBack()
                },
                title = "Confirmation",
                text = "Are you sure want to save it?"
            )
        }

        Box(modifier = Modifier.fillMaxWidth()) {
            IconButton(
                onClick = { onBack() },
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .size(24.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = "Back",
                )
            }
            Text(
                text = "Add New Data",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Image(
            painter = if (photo == null) {
                painterResource(id = R.drawable.baseline_default_photo_24)
            }
            else {
                val image = convertImageByteArrayToBitmap(photo!!)
                BitmapPainter(image.asImageBitmap())
            },
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(60.dp))
                .clickable {
                    showImagePicker = true
                },
            contentScale = ContentScale.Crop,
            colorFilter = if (photo == null) {
                ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
            } else null
        )
        Spacer(modifier = Modifier.height(16.dp))
        CustomTextBox(
            text = nama,
            onTextChanged = {nama = it},
            hint = "Nama"
        )
        CustomTextBox(
            text = namaPanggilan,
            onTextChanged = {namaPanggilan = it},
            hint = "Nama Panggilan"
        )
        CustomTextBox(
            text = email,
            onTextChanged = {email = it},
            hint = "Email"
        )
        CustomTextBox(
            text = alamat,
            onTextChanged ={alamat = it},
            hint = "Alamat"
        )
        DatePickerTextField(
            text = tglLahir,
            onClick = {showDatePicker = true}
        )
        if (showDatePicker) {
            DatePickerDocked(
                date = tglLahir,
                onValueChange = {
                    tglLahir = it
                    showDatePicker = false
                },
                onDismiss = { showDatePicker = false }
            )
        }
        CustomTextBox(
            text = noHP,
            onTextChanged = {noHP = it},
            hint = "No. HP"
        )
        Spacer(modifier = Modifier.height(16.dp))
        CustomButton(
            text = "Save"
        ) {
            if (nama.isEmpty() || namaPanggilan.isEmpty() || email.isEmpty() || tglLahir.isEmpty() || noHP.isEmpty()){
                Toast.makeText(context, "All field must be filled", Toast.LENGTH_SHORT).show()
                return@CustomButton
            }
            if (photo == null){
                Toast.makeText(context, "Please select Photo to save", Toast.LENGTH_SHORT).show()
                return@CustomButton
            }
            showConfirmation = true
        }
    }
}

@Composable
fun DialogPickImage(
    onCameraSelected: ()-> Unit,
    onGallerySelected: ()-> Unit,
    onDismissRequest: ()-> Unit
) {
    AlertDialog(
        onDismissRequest = { onDismissRequest() },
        confirmButton = {},
        title = { Text(
            text = "Select method",
            color = MaterialTheme.colorScheme.primary
        )},
        text = {
            Column {
                TextButton(
                    onClick = { onCameraSelected() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Camera")
                }
                TextButton(
                    onClick = { onGallerySelected() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Gallery")
                }
            }
        }
    )
}

fun convertImageByteArrayToBitmap(imageData: ByteArray): Bitmap {
    return BitmapFactory.decodeByteArray(imageData, 0, imageData.size)
}

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    OurBookTheme {
        SaveDataScreen(onBack = {}, existingDataUserID = -1)
    }
}