package com.example.myapplication.api

data class CatData(
    val id: Int,
    val url: String,
    val webpurl: String,
    val x: Double,
    val y: Double
)


data class DuckData(
    val message: String,
    val url: String
)