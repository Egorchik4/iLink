package com.example.myapplication.api

import retrofit2.http.GET

const val BASE_URLone = "https://thatcopy.pw/catapi/"
const val BASE_URLtwo = "https://random-d.uk/api/"


interface CatRequest {

    @GET("rest")
    suspend fun getRandomCat(): CatData // возвращает тип CatData

}


interface DuckRequest {

    @GET("random")
    suspend fun getRandomDuck(): DuckData

}