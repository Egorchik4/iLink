package com.example.myapplication.screens

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.api.BASE_URLone
import com.example.myapplication.api.BASE_URLtwo
import com.example.myapplication.api.CatRequest
import com.example.myapplication.api.DuckRequest
import com.example.myapplication.databinding.MenuFragmentBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class MenuFragment : Fragment() {

    lateinit var binding: MenuFragmentBinding  // название разметки layout + 'binding'
    private val ViewMod: MenuViewModel by activityViewModels()  // класс ViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = MenuFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // назавание на кнопках
        binding.buttoncat.text = "CAT"
        binding.buttonduck.text = "DUCK"



        binding.buttonduck.setOnClickListener {
            ViewMod.animation(binding.buttoncat,binding.buttonduck)
            ViewMod.showdug(binding.imageView)

        }
        binding.buttoncat.setOnClickListener {
            ViewMod.animation(binding.buttoncat,binding.buttonduck)
            ViewMod.showcat(binding.imageView)
        }

    }


}