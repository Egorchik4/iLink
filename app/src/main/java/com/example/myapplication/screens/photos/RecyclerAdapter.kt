package com.example.myapplication.screens.photos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.Data
import com.example.myapplication.databinding.PhotoItemBinding

class RecyclerAdapter() : RecyclerView.Adapter<RecyclerAdapter.PhotoHolder>() {

    lateinit var photoList: MutableList<String> // лист ячеек списка тип String (отложенная инициализация)

    class PhotoHolder(item: View):RecyclerView.ViewHolder(item) {  // принимаем раздутую View(ячейку списка) из onCreateViewHolder
        val binding = PhotoItemBinding.bind(item) // viewBinding

        fun bind(photostr: String)  // заполнение View
        {
            Glide.with(itemView).load(photostr).into(binding.imageView2)  // photostr - путь в кэше, где находиться картинка
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.photo_item,parent,false) // надуваем View(ячейку списка)
        return PhotoHolder(view) // возвращаем класс с надутой View
    }

    override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
        holder.bind(photoList[position])  // отправляем данные
    }

    override fun getItemCount(): Int {
        return photoList.size

    }



    fun addAll(lst: MutableList<String>?){

        if (lst != null){
            photoList = lst
            notifyDataSetChanged()  // обновление списка для отображения
        }
    }
}