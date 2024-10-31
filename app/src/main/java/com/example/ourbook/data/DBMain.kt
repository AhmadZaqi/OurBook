package com.example.ourbook.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.ourbook.model.DataUser

class DBMain(context: Context): SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    companion object{
        private const val DB_NAME = "OurBook"
        private const val DB_VERSION = 1
        private const val TABLE_DATA_USER  = "DataUser"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query = "create table $TABLE_DATA_USER (" +
                "ID integer primary key autoincrement," +
                "Nama text not null," +
                "NamaPanggilan not null," +
                "Photo blob not null," +
                "Email text not null," +
                "Alamat text," +
                "TglLahir text not null," +
                "NoHP text not null)"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val query = "drop table if exists $TABLE_DATA_USER"
        db?.execSQL(query)
        onCreate(db)
    }

    fun findData(id: Int): DataUser{
        val db = readableDatabase
        var data: DataUser? = null
        val filter = "ID = ?"
        val cursor = db.query(TABLE_DATA_USER, null, filter, arrayOf(id.toString()), null, null, null)

        while (cursor.moveToNext()){
            val id = cursor.getInt(cursor.getColumnIndexOrThrow("ID"))
            val nama = cursor.getString(cursor.getColumnIndexOrThrow("Nama"))
            val namaPanggilan = cursor.getString(cursor.getColumnIndexOrThrow("NamaPanggilan"))
            val photo = cursor.getBlob(cursor.getColumnIndexOrThrow("Photo"))
            val email = cursor.getString(cursor.getColumnIndexOrThrow("Email"))
            val alamat = cursor.getString(cursor.getColumnIndexOrThrow("Alamat"))
            val tglLahir = cursor.getString(cursor.getColumnIndexOrThrow("TglLahir"))
            val noHP = cursor.getString(cursor.getColumnIndexOrThrow("NoHP"))

            data = DataUser(id, nama, namaPanggilan, photo, email, alamat, tglLahir, noHP)
        }
        cursor.close()
        db.close()

        return data!!
    }

    fun editData(data: DataUser){
        val db = readableDatabase
        val values = ContentValues().apply {
            put("Nama", data.nama)
            put("NamaPanggilan", data.namaPanggilan)
            put("Photo", data.photo)
            put("Email", data.email)
            put("Alamat", data.alamat)
            put("TglLahir", data.tglLahir)
            put("NoHP", data.noHP)
        }
        val filter = "ID = ?"
        db.update(TABLE_DATA_USER, values, filter, arrayOf(data.id.toString()))
        db.close()
    }

    fun getAllData(): MutableList<DataUser>{
        val result = mutableListOf<DataUser>()
        val db = readableDatabase
        val query = "select * from $TABLE_DATA_USER"
        val cursor = db.rawQuery(query, null)

        while (cursor.moveToNext()){
            val id = cursor.getInt(cursor.getColumnIndexOrThrow("ID"))
            val nama = cursor.getString(cursor.getColumnIndexOrThrow("Nama"))
            val namaPanggilan = cursor.getString(cursor.getColumnIndexOrThrow("NamaPanggilan"))
            val photo = cursor.getBlob(cursor.getColumnIndexOrThrow("Photo"))
            val email = cursor.getString(cursor.getColumnIndexOrThrow("Email"))
            val alamat = cursor.getString(cursor.getColumnIndexOrThrow("Alamat"))
            val tglLahir = cursor.getString(cursor.getColumnIndexOrThrow("TglLahir"))
            val noHP = cursor.getString(cursor.getColumnIndexOrThrow("NoHP"))

            val data = DataUser(id, nama, namaPanggilan, photo, email, alamat, tglLahir, noHP)
            result.add(data)
        }
        cursor.close()
        db.close()

        return result
    }

    fun insertData(data: DataUser){
        val db = readableDatabase
        val values = ContentValues().apply {
            put("Nama", data.nama)
            put("NamaPanggilan", data.namaPanggilan)
            put("Photo", data.photo)
            put("Email", data.email)
            put("Alamat", data.alamat)
            put("TglLahir", data.tglLahir)
            put("NoHP", data.noHP)
        }
        db.insert(TABLE_DATA_USER, null, values)
        db.close()
    }

    fun deleteData(id: Int){
        val db = readableDatabase
        val filter = "ID = ?"
        db.delete(TABLE_DATA_USER, filter, arrayOf(id.toString()))
        db.close()
    }
}