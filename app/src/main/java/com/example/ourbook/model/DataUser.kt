package com.example.ourbook.model

import java.nio.ByteBuffer

data class DataUser(
    val id: Int,
    val nama: String,
    val namaPanggilan: String,
    val photo: ByteArray,
    val email: String,
    val alamat: String?,
    val tglLahir: String,
    val noHP: String
)
