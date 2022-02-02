package com.example.myapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.screens.menu.MenuFragment

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