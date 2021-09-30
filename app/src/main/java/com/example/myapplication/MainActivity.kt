package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.myapplication.api.BASE_URLone
import com.example.myapplication.api.BASE_URLtwo
import com.example.myapplication.api.CatRequest
import com.example.myapplication.api.DuckRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var imageView: ImageView





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        imageView = findViewById(R.id.imageView) // for Glad

    }


    fun showcat (view: View) {
        // инициализация объекта
        val buttonone = findViewById(R.id.buttoncat) as TextView
        val buttontwo = findViewById(R.id.buttonduck) as TextView



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
            val buttonaim = AnimationUtils.loadAnimation(this,R.anim.buttonaim)
            buttonone.startAnimation(buttonaim)


            val buttonaimtwo = AnimationUtils.loadAnimation(this,R.anim.buttonaim2)
            buttontwo.startAnimation(buttonaimtwo)



        }











        val api = Retrofit.Builder()
            .baseUrl(BASE_URLone)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CatRequest::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val responsecat = api.getRandomCat()

                withContext(Dispatchers.Main) {
                    Glide.with(applicationContext).load(responsecat.url).into(imageView)
                    imageView.visibility = View.VISIBLE   // показ картинки


                }
            } catch (e: Exception) {
                Log.e("Main", "Error: ${e.message}")
            }
        }

        //imageView.visibility = View.VISIBLE   // показ картинки

    }

    fun showdug (view: View) {

        // инициализация объекта
        val buttonone = findViewById(R.id.buttoncat) as TextView
        val buttontwo = findViewById(R.id.buttonduck) as TextView



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
            paramstwo.topMargin = 1100       // вниз
            //params.bottomMargin = 200     // вверх
            //params.rightMargin = 300        // налево
            buttontwo.layoutParams = paramstwo



            // lunch animation
            val buttonaim = AnimationUtils.loadAnimation(this,R.anim.buttonaim)
            buttonone.startAnimation(buttonaim)


            val buttonaimtwo = AnimationUtils.loadAnimation(this,R.anim.buttonaim2)
            buttontwo.startAnimation(buttonaimtwo)



        }










        val api = Retrofit.Builder()
            .baseUrl(BASE_URLtwo)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DuckRequest::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val responsecat = api.getRandomDuck()

                withContext(Dispatchers.Main) {
                    Glide.with(applicationContext).load(responsecat.url).into(imageView)
                    imageView.visibility = View.VISIBLE   // показ картинки

                }
            } catch (e: Exception) {
                Log.e("Main", "Error: ${e.message}")
            }
        }




    }



}