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

    // нумерация сохраняемых файлов
    private var MutLiveInt = MutableLiveData<Int>()
    val LiveInt: LiveData<Int> = MutLiveInt

    // состояние списка(отметка списка)
    private var RecyclLive = MutableLiveData<Int?>()
    val RecLive: LiveData<Int?> = RecyclLive

    // selectAll
    private var MutSelectLive = MutableLiveData<Int?>()
    val SelectLive: LiveData<Int?> = MutSelectLive

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
        selectInt(0)
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

    // selectAll
    fun selectInt(t: Int){
        MutSelectLive.value = t
    }

    // список на удаление
    fun DeleteList(positionList: MutableList<String>){
        Log.e("eee",positionList.size.toString()+" size")
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