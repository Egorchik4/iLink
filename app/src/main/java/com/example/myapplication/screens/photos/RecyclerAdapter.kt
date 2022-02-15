package com.example.myapplication.screens.photos

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.myapplication.R
import com.example.myapplication.databinding.PhotoItemBinding

//interface UserActionListener{
    //fun onUserDelete(user: MutableList<String>)

   // fun onUserDetails(user: MutableList<String>)
//}


class RecyclerAdapter() : RecyclerView.Adapter<RecyclerAdapter.PhotoHolder>() {

    var photoList: MutableList<String>? = mutableListOf() // лист ячеек списка тип String (отложенная инициализация)
    var delateList: MutableList<String>? = mutableListOf() // спискок хранения позиций на удаление
    var visibleCheck: Int = 0


    // ???
    class PhotoHolder(item: View) : RecyclerView.ViewHolder(item) {  // принимаем раздутую View(ячейку списка) из onCreateViewHolder
        val bindingg = PhotoItemBinding.bind(item) // viewBinding
        fun bind(photostr: String,t: Int, position: Int)  // заполнение View
        {
            Glide.with(itemView)
                .load(photostr)
                .diskCacheStrategy(DiskCacheStrategy.NONE)  // не кэшировать изобраения
                .into(bindingg.imageView2)  // photostr - путь в кэше, где находиться картинка

            bindingg.checkBox.visibility = View.GONE
        }


        // Обработка нажатия на элемент в списке
        //init {
         //   bindingg.imageView2.setOnClickListener {
         //       val text: String = "$position"
         //       val duration = Toast.LENGTH_SHORT
         //       val toast = Toast.makeText(bindingg.imageView2.context, text, duration)
          //      toast.show()
          //  }

        //}

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.PhotoHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.photo_item, parent, false) // надуваем View(ячейку списка)
        return RecyclerAdapter.PhotoHolder(view) // возвращаем класс с надутой View
    }

    // все действия со списком происходят здесь(не обязательно)
    override fun onBindViewHolder(holder: RecyclerAdapter.PhotoHolder, position: Int) {
        holder.bind(photoList!![position],visibleCheck,position)  // отправляем данные текущей позиции
        //holder.bindingg.checkBox.visibility = View.VISIBLE  // РАБОТАЕТ!!!
        Log.e("eee", visibleCheck.toString() + " C")
        holder.bindingg.checkBox.visibility = View.GONE

        if (visibleCheck != 0){
            holder.bindingg.imageView2.setOnClickListener {
                val text: String = "$position"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(holder.bindingg.imageView2.context, text, duration)
                toast.show()


                dalateById(photoList!![position])
                holder.bindingg.checkBox.visibility = View.VISIBLE
                holder.bindingg.checkBox.setChecked(true)
            }
        }else{
            //holder.bindingg.checkBox.visibility = View.GONE
        }

        //if (holder.bindingg.checkBox.isChecked){


            //dalateById(position)
        //}

        //holder.bindingg.imageView2.setOnClickListener {
       //     onClick.onClicked(photoList!![position])
        //}

    }

    override fun getItemCount(): Int {
        return photoList!!.size
    }


    // обновление списка
    fun addAll(lst: MutableList<String>?) {
        photoList = lst
        //Log.e("eee", photoList?.size.toString() + " addALL")

        if (photoList != null) {
            notifyDataSetChanged()  // обновление списка для отображения
        }
    }


    // для изменения вида списка
    fun check(e: Int){
        visibleCheck = e
        //Log.e("eee", visibleCheck.toString() + " check")
        notifyDataSetChanged()
    }




    private fun dalateById(t: String){
        delateList?.add(t)
        Log.e("eee", delateList?.size.toString() + " LLLL")
    }

    fun cleardealeteList(){
        Log.e("eee", delateList?.size.toString() + " delateLLLL")
        delateList?.clear()

    }

    fun getDeleteList(): MutableList<String>? {
        return delateList
    }


}