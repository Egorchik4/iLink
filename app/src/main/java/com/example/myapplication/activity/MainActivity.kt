package com.example.myapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.api.BASE_URLone
import com.example.myapplication.api.BASE_URLtwo
import com.example.myapplication.api.CatRequest
import com.example.myapplication.api.DuckRequest
import com.example.myapplication.screens.MenuFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    // override - переопределние функции, которая унаследована от открытого или абстрактного класса
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //показ MenuFragment
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, MenuFragment())
            .commit()

    }

}