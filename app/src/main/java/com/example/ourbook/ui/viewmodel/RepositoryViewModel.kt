package com.example.ourbook.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.ourbook.data.DBMain
import com.example.ourbook.model.DataUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RepositoryViewModel: ViewModel() {
    private val _listData = MutableStateFlow(listOf<DataUser>())
    private val _selectedData = MutableStateFlow<DataUser?>(null)

    val listData = _listData.asStateFlow()
    val selectedData = _selectedData.asStateFlow()

    fun getAllData(context: Context){
        val data = DBMain(context).getAllData()
        _listData.value = data
    }

    fun insertNewData(context: Context, dataUser: DataUser){
        DBMain(context).insertData(dataUser)
    }

    fun findData(context: Context, id: Int){
        if (id == -1) return
        val data = DBMain(context).findData(id)
        _selectedData.value = data
    }

    fun editData(context: Context, data: DataUser){
        DBMain(context).editData(data)
    }

    fun deleteData(context: Context, id: Int){
        DBMain(context).deleteData(id)
    }
}