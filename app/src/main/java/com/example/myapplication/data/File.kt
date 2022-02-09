package com.example.myapplication.data

import android.util.Log
import android.view.View
import java.io.File

class FileSave {

    // сделать так, что если есть папка, то не пересоздаём её!!!
    fun createpackage(v: View): String{
        val folderToSave: String = v.context?.getCacheDir().toString()+"/photo" // путь к папке куда будем сохранять (photo)
        val photoDirectory = File(folderToSave)
        photoDirectory.mkdir()  // создание папки
        return photoDirectory.toString()  // возварат полного пути папки
    }

}