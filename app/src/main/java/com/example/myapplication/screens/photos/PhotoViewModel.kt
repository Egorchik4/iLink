package com.example.myapplication.screens.photos

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.io.File
import java.io.FileReader

class PhotoViewModel : ViewModel() {

    private var SaveLive = MutableLiveData<MutableList<String>?>()
    val SLive: LiveData<MutableList<String>?> = SaveLive

    private var MutLiveInt = MutableLiveData<Int>()
    val LiveInt: LiveData<Int> = MutLiveInt

    var productSet: MutableList<String>? = mutableListOf()  // список адресов
    val path: String = "/data/user/0/com.example.myapplication/cache/photo"

    init{
        readFile(path)  // ссылка на папку
        Log.e("eee","init")
    }

    fun saveInt(r: Int){
        MutLiveInt.value = r
    }

    private fun refreshdata(){
        SaveLive.value = productSet
        saveInt(productSet!!.size)
        Log.e("eee",SaveLive.value?.size.toString()+" is Directory")
    }

    fun savepath(d: String){
        productSet?.add(d)
        refreshdata()
    }

    fun delateAL(){
        productSet?.clear()
        refreshdata()

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

    // считывание адресов фото(которые уже есть в памяти телефона) и заполненение (выполняется только при запуске приложения)
    fun readFile(path: String){
        val r = File(path)
        saveInt(r.listFiles().size)
        // проверка на существование папки на пути(path)
        if(r.isDirectory){
            for(i in 0..(r.listFiles().size-1)){
                productSet?.add(r.listFiles().get(i).toString())  // заполнение нашего списка адресами файлов
            }
            refreshdata()
        }
    }




}