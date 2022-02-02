package com.example.myapplication.screens.menu

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.api.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class MenuViewModel : ViewModel() {

    private val downoload = ApiClass()  // объект класса private-не виден вне класса

    // Создание LiveData
    // для
    private var MutLive = MutableLiveData<Int>()
    val Live: LiveData<Int> = MutLive


    //private var MutLiveInternet = MutableLiveData<String>()
    //val LiveInter: LiveData<String> = MutLiveInternet





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

    fun showcat (view: View) {
        //MutLiveInternet.value =
        downoload.RandomPhoto(view, BASE_URLone, "CAT")

    }

    fun showdug (view: View) {
        downoload.RandomPhoto(view, BASE_URLtwo,"DUCK")

    }

}