package com.example.myapplication.screens

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
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

    private val downoload = ApiClass()


    fun animation(viewone: View, viewtwo: View) {
        // инициализация объекта
        val buttonone = viewone as TextView
        val buttontwo = viewtwo as TextView

        var params = buttonone.layoutParams as ViewGroup.MarginLayoutParams
        var paramstwo = buttontwo.layoutParams as ViewGroup.MarginLayoutParams


        if (params.topMargin != 1100){
            // переезд на новое место элемента

            //params.leftMargin = 300        // направо
            params.topMargin = 1100         // вниз
            //params.bottomMargin = 200     // вверх
            //params.rightMargin = 300        // налево
            buttonone.layoutParams = params

            //params.leftMargin = 300        // направо
            paramstwo.topMargin = 1100        // вниз
            //params.bottomMargin = 200     // вверх
            //params.rightMargin = 300        // налево
            buttontwo.layoutParams = paramstwo

            // lunch animation
            val buttonaim = AnimationUtils.loadAnimation(viewone.context,R.anim.buttonaim)
            buttonone.startAnimation(buttonaim)


            val buttonaimtwo = AnimationUtils.loadAnimation(viewtwo.context, R.anim.buttonaim2)
            buttontwo.startAnimation(buttonaimtwo)



        }
    }



    fun showcat (view: View) {
        downoload.RandomPhoto(view, BASE_URLone, "CAT")

    }

    fun showdug (view: View) {
        downoload.RandomPhoto(view, BASE_URLtwo,"DUCK")

    }

}