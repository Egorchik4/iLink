package com.example.myapplication.screens.menu

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.api.*
import com.example.myapplication.data.FileSave
import com.example.myapplication.screens.photos.PhotoViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.lang.Exception

class MenuViewModel : ViewModel() {

    //private val VMP = PhotoViewModel()  // ?????

    private val fi = FileSave()  // класс для работы с файлами
    private val downoload = ApiClass()  // объект класса private-не виден вне класса

    // Создание LiveData
    // для
    private var MutLive = MutableLiveData<Int>()
    val Live: LiveData<Int> = MutLive


    fun animation(viewone: View, viewtwo: View, t: Int) {
        // инициализация объекта
        val buttonone = viewone as TextView
        val buttontwo = viewtwo as TextView

        var params = buttonone.layoutParams as ViewGroup.MarginLayoutParams
        var paramstwo = buttontwo.layoutParams as ViewGroup.MarginLayoutParams

        if (t != Live.value){
            // переезд на новое место элемента
            //params.leftMargin = 300        // направо
            params.topMargin = t         // вниз
            //params.bottomMargin = 200     // вверх
            //params.rightMargin = 300        // налево
            buttonone.layoutParams = params

            //params.leftMargin = 300        // направо
            paramstwo.topMargin = t        // вниз
            //params.bottomMargin = 200     // вверх
            //params.rightMargin = 300        // налево
            buttontwo.layoutParams = paramstwo

            // lunch animation
            val buttonaim = AnimationUtils.loadAnimation(viewone.context,R.anim.buttonaim)
            buttonone.startAnimation(buttonaim)

            val buttonaimtwo = AnimationUtils.loadAnimation(viewtwo.context, R.anim.buttonaim2)
            buttontwo.startAnimation(buttonaimtwo)

            MutLive.value = t  // LiveData
        }
    }

    fun saveFile(photo: ImageView,n: Int): String?{
        var pathtofile: String?

        try{
            // сохранение фото
            var iv: ImageView = photo  // ImageView, содержащий изображение, которое нужно сохранить
            Log.e("eee", " 1")
            val draw = iv.drawable
            Log.e("eee", " 2")
            val bitmap = (draw as BitmapDrawable).bitmap  // взятие bitmap картинки, котрая содержится в ImageView
            Log.e("eee", " 3")


            // создание папки для хранение фото
            val file = File(fi.createpackage(photo),"test$n.jpg")  // объект класса File
            Log.e("eee", " 4")
            val stream: OutputStream = FileOutputStream(file)
            Log.e("eee", " 5")

            bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream)  // сжатие файла 100 - не сжимаем
            stream.flush()
            stream.close()

            pathtofile = Uri.parse(file.absolutePath).toString()

            Toast.makeText(photo.context,"Like and Save", Toast.LENGTH_SHORT).show()
            Toast.makeText(photo.context,"Image save${Uri.parse(file.absolutePath)}", Toast.LENGTH_SHORT).show()

        }catch (e: Exception){
            Toast.makeText(photo.context,"Error", Toast.LENGTH_SHORT).show()
            pathtofile = null
        }
        return pathtofile

    }

    fun showcat (view: View) {
        downoload.RandomPhoto(view, BASE_URLone, "CAT")
    }

    fun showdug (view: View) {
        downoload.RandomPhoto(view, BASE_URLtwo,"DUCK")
    }


}