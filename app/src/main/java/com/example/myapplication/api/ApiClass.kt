package com.example.myapplication.api

import android.util.Log
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.lang.Exception

class ApiClass {

    fun RandomPhoto(view: View, BASE_URL: String, clas: String){

        if (clas == "CAT"){
            val api = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CatRequest::class.java)

            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val responsecat = api.getRandomCat()

                    withContext(Dispatchers.Main) {

                        Glide.with(view.context)
                            .load(responsecat.url)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)  // не кэшировать изобраения
                            .centerCrop()
                            .into(view as ImageView) //

                        view.visibility = View.VISIBLE   // показ картинки

                    }
                } catch (e: Exception) {
                    Log.e("Main", "Error: ${e.message}")
                }
            }
        }else{
            val api = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(DuckRequest::class.java)

            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val responsecat = api.getRandomDuck()

                    withContext(Dispatchers.Main) {

                        Glide.with(view.context)
                            .load(responsecat.url)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)  // не кэшировать изобраения
                            .centerCrop()
                            .into(view as ImageView) //???

                        view.visibility = View.VISIBLE   // показ картинки

                    }
                } catch (e: Exception) {
                    Log.e("Main", "Error: ${e.message}")
                }
            }

        }

    }



}