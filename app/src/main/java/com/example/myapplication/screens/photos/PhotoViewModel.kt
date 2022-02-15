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

    private var SaveLive = MutableLiveData<MutableList<String>?>()
    val SLive: LiveData<MutableList<String>?> = SaveLive

    private var MutLiveInt = MutableLiveData<Int>()
    val LiveInt: LiveData<Int> = MutLiveInt

    private var RecyclLive = MutableLiveData<Int?>()
    val RecLive: LiveData<Int?> = RecyclLive

    private var delateLive = MutableLiveData<Int?>()
    val DelateLive: LiveData<Int?> = delateLive

    // список адресов
    var productSet: MutableList<String>? = mutableListOf()
    val path: String = "/data/user/0/com.example.myapplication/cache/photo"


    var fileclass = FileSave()

    init{
        // считывание адресов фото(которые уже есть в памяти телефона) и заполненение списка адресов (выполняется только при запуске приложения)
        fileclass.readFile(path,productSet) // ссылка на папку

        // инициализации LiveDatas
        saveInt(productSet?.size ?: 0, 0)
        saveInt(0, 1)
        refreshdata()
        Log.e("eee","init")
    }

    fun saveInt(r: Int, key: Int){
        if (key == 0){
            MutLiveInt.value = r
        }else{
            RecyclLive.value = r
        }
    }

    private fun refreshdata(){
        SaveLive.value = productSet
        saveInt(productSet?.size ?: 0, 0)  //элвис оператор (если левая часть null, то выполняй правую)
        //Log.e("eee",SaveLive.value?.size.toString()+" is Directory")
    }

    fun savepath(d: String){
        productSet?.add(d)
        refreshdata()
    }


    fun delateById(positionList: MutableList<String>){
        Log.e("eee",positionList.size.toString()+" positionList")

        // ???
        if (positionList.size != 0 && productSet?.size != null){

            for(i in 0..(positionList.size-1)){
                //Log.e("eee", i.toString()+" iteration:")
                //Log.e("eee", productSet?.size.toString()+" !!")

                productSet?.removeIf {
                    it.contains(positionList[i])
                }

                fileclass.delateAL(positionList[i])
                //Log.e("eee", productSet?.size.toString()+" !!!")
            }
            refreshdata()
        }


    }

    fun delateAL(){
        productSet?.clear()
        refreshdata()
        fileclass.delateAL(path)
    }







}