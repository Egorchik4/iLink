package com.example.myapplication.screens.photos

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.FileSave
import java.io.File
import java.io.FileReader

class PhotoViewModel : ViewModel() {

    // Добавление элементов
    private var AddLive = MutableLiveData<MutableList<String>?>()
    val addLive: LiveData<MutableList<String>?> = AddLive

    // Удаление элементов
    private var DeleteLive = MutableLiveData<MutableList<String>?>()
    val deleteLive: LiveData<MutableList<String>?> = DeleteLive



    private var MutLiveInt = MutableLiveData<Int>()
    val LiveInt: LiveData<Int> = MutLiveInt

    private var RecyclLive = MutableLiveData<Int?>()
    val RecLive: LiveData<Int?> = RecyclLive

    // список адресов
    var productSet: MutableList<String>? = mutableListOf()
    val path: String = "/data/user/0/com.example.myapplication/cache/photo" // место сохранения файлов

    var fileclass = FileSave()

    init{
        // считывание адресов фото(которые уже есть в памяти телефона) и заполненение списка адресов (выполняется только при запуске приложения)
        fileclass.readFile(path,productSet) // ссылка на папку
        // инициализации LiveDatas
        AddLive.value = productSet
        saveInt(productSet?.size ?: 0, 0)
        saveInt(0, 1)
        Log.e("eee","init")
    }

    // заполнение списка
    fun AddPath(path: String){
        productSet?.add(path)  // заполнение списка
        AddLive.value = productSet  // обновление AddLive
        saveInt(productSet?.size ?: 0, 0)  //элвис оператор (если левая часть null, то выполняй правую)
    }


    fun saveInt(r: Int, key: Int){
        if (key == 0){
            MutLiveInt.value = r
        }else{
            RecyclLive.value = r
        }
    }


    fun DeleteList(positionList: MutableList<String>){
        if (positionList.size != 0 && productSet?.size != null){
            for(i in 0..(positionList.size-1)){
                productSet?.removeIf {
                    it.contains(positionList[i])  // удаление по значению(адресу)
                }
                fileclass.delateAL(positionList[i])
            }
            saveInt(productSet?.size ?: 0, 0)
            DeleteLive.value = positionList // обновление DeleteLive

        }
    }




}