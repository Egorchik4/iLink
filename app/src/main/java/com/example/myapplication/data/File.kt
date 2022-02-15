package com.example.myapplication.data

import android.util.Log
import android.view.View
import java.io.File

class FileSave {

    // сделать так, что если есть папка, то не пересоздаём её!!!
    fun createpackage(v: View): String{
        val folderToSave: String = v.context?.getCacheDir().toString()+"/photo" // путь к папке куда будем сохранять (photo)
        val photoDirectory = File(folderToSave)
        if(photoDirectory.isDirectory){
            Log.e("eee"," isDirectory")
        }else{
            photoDirectory.mkdir()  // создание папки
            Log.e("eee"," no Directory")
        }
        return photoDirectory.toString()  // возварат полного пути папки
    }


    fun readFile(path: String, photoset: MutableList<String>?){
        val r: File? = File(path)
        //saveInt(r?.listFiles()?.size ?: 0)  // элвис опертор (елси r не null и listFiles() не null вызвать size, иначе 0)
        // проверка на существование папки на пути(path)
        if(r != null && r.isDirectory && r.listFiles()?.size != null){

            var n: Int = r.listFiles().size
            if(n != 0){
                for(i in 0..n-1){
                    photoset?.add(r.listFiles().get(i).toString())  // заполнение нашего списка адресами файлов
                }
            }

        }
    }

    fun delateAL(path: String){
        val t = File(path)
        //Log.e("eee",t.isDirectory.toString()+" Directory")

        if (t.isDirectory){
            //очистка папки(Directory)
            for(i in 0..(t.listFiles().size-1)){
                t.listFiles().get(0).delete()
            }
            // удаление самой папки
            t.delete()
        } else if (t.isFile){
            t.delete()
        }
    }

}