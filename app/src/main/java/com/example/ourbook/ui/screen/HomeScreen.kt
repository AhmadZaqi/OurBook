package com.example.ourbook.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.ColorFilter
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
import com.example.ourbook.ui.components.DialogBox
import com.example.ourbook.ui.components.ItemDisplay
import com.example.ourbook.ui.theme.OurBookTheme
import com.example.ourbook.ui.viewmodel.RepositoryViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onEditData: (Int) -> Unit,
    navToDetailScreen: (Int)-> Unit,
    viewModel: RepositoryViewModel = viewModel()
) {
    val context = LocalContext.current
    val listData by viewModel.listData.collectAsState()
    var showDialog by remember {
        mutableStateOf(false)
    }
    var selectedData by remember {
        mutableStateOf<DataUser?>(null)
    }

    LaunchedEffect(key1 = listData) {
        viewModel.getAllData(context)
    }

    if (showDialog){
        selectedData?.let {
            DialogBox(
                onDismissRequest = { showDialog = false },
                onConfirmation = {
                    viewModel.deleteData(context, it.id)
                    showDialog = false
                },
                title = "Confirmation",
                text = "Are you sure want to delete \"${it.nama}\"?"
            )
        }
    }
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
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
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(listData, key = {it.id}) { data ->
                ItemDisplay(
                    data = data,
                    onEditData = {
                        selectedData = data
                        onEditData(data.id)
                    },
                    onRemoveData = {
                        selectedData = data
                        showDialog = true
                    },
                    modifier = Modifier.clickable { navToDetailScreen(data.id) }
                )
            }
        }
    }
}