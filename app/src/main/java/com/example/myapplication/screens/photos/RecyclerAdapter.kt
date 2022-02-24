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
import kotlinx.coroutines.delay

class RecyclerAdapter() : RecyclerView.Adapter<RecyclerAdapter.PhotoHolder>() {

    var photoList: MutableList<String>? = mutableListOf() // лист ячеек списка тип String (отложенная инициализация)
    var delateList: MutableList<String> = mutableListOf() // спискок хранения позиций на удаление
    var visibleCheck: Int = 0
    var activateCheck: Int = 0

    // добавление элемента в начало списка
    fun addItem(newItem: String, index: Int){
        //Log.e("eee", newItem+" $index")
        photoList?.add(newItem)
        //Log.e("eee", photoList?.size.toString()+" photoList.size")
        notifyItemChanged(index)
    }

    // удаление элементов
    fun deleteItem(index: Int){
        //val position = newItem.indexOf(newItem)
        Log.e("eee", index.toString()+"deleteItem")
        photoList?.removeAt(index)
        notifyItemChanged(index)
    }

    // вставка на нужную позицию
    ///fun updateItem(position: Int,newItem: String){
        //photoList[position] = newItem
    //    notifyItemChanged(position)
    //}


    class PhotoHolder(item: View) : RecyclerView.ViewHolder(item) {  // принимаем раздутую View(ячейку списка) из onCreateViewHolder
        val bindingg = PhotoItemBinding.bind(item) // viewBinding

        fun bind(photostr: String)  // заполнение View
        {
            Glide.with(itemView)
                .load(photostr)
                .diskCacheStrategy(DiskCacheStrategy.NONE)  // не кэшировать изобраения
                .into(bindingg.imageView2)  // photostr - путь в кэше, где находиться картинка
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.PhotoHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.photo_item, parent, false) // надуваем View(ячейку списка)
        return RecyclerAdapter.PhotoHolder(view) // возвращаем класс с надутой View
    }

    // все действия со списком происходят здесь(не обязательно)
    override fun onBindViewHolder(holder: RecyclerAdapter.PhotoHolder, position: Int) {
        holder.bind(photoList!![position])  // отправляем данные текущей позиции

        // выделение всех объектов
        if(activateCheck == 1){
            holder.bindingg.checkBox.visibility = View.VISIBLE
            holder.bindingg.checkBox.setChecked(true)
            //Log.e("eee", " $position position")
            dalateById(photoList!![position])  // заполнение списка на удаление
            holder.bindingg.checkBox.setOnClickListener {
                if(holder.bindingg.checkBox.isChecked){
                    Log.e("eee", " $position check")
                }else{
                    // !!!
                    delateBy(photoList!![position])  // удаление из списка удаления
                }
            }
        }else{
            holder.bindingg.checkBox.visibility = View.GONE
            holder.bindingg.checkBox.setChecked(false)
            cleardealeteList()  // снятие галочек
        }

        // видимость объектов
        if(visibleCheck != 0){
            holder.bindingg.checkBox.visibility = View.VISIBLE

            holder.bindingg.imageView2.setOnClickListener {
                val text: String = "$position"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(holder.bindingg.imageView2.context, text, duration)
                toast.show()
                holder.bindingg.checkBox.setChecked(true)

                // !!!
                dalateById(photoList!![position])

                holder.bindingg.checkBox.setOnClickListener {
                    if(holder.bindingg.checkBox.isChecked){
                        Log.e("eee", " $position check")
                    }else{
                        // !!!
                        delateBy(photoList!![position])

                    }
                }

            }
        }else{
            holder.bindingg.imageView2.setOnClickListener(null) // удаления слушателя нажатий
            holder.bindingg.checkBox.visibility = View.GONE
            holder.bindingg.checkBox.setChecked(false)
        }
    }

    override fun getItemCount(): Int {
        return photoList!!.size
    }

    // обновление списка
    fun addAll(lst: MutableList<String>?) {
        if(photoList != null) {
            // добавление элементов
            if (photoList!!.size < lst!!.size) {
                var n: Int = lst!!.size - photoList!!.size
                if(n == lst!!.size){
                    n = 0
                }
                for (i in n..(lst!!.size - 1)) {
                    addItem(lst.get(i), i)
                }
            }
        }
    }

    // Удаление элементов списка
    fun deleteALL(lst: MutableList<String>?){
        Log.e("eee", "deleteALL "+lst?.size.toString())
        // удаление элементов
        if(photoList != null) {
            for(i in 0..(lst!!.size-1)){
                deleteItem(photoList!!.indexOf(lst!!.get(i)))
            }
        }
    }

    // для изменения вида списка
    fun check(e: Int){
        visibleCheck = e
        notifyDataSetChanged()
    }

    // выделение всех элементов в списке
    fun activatecheck(e: Int){
        activateCheck = e
        notifyDataSetChanged()
    }

    // формирование списка на удаление
    fun dalateById(t: String){
        delateList.add(t)
        //Log.e("eee", delateList.size.toString()+" deleteSize")
    }

    // корректировка списка на удаление
    fun delateBy(d: String){
        for(i in 0..(delateList.size-1)){
            delateList.removeIf {
                it.contains(d)
            }
        }
    }

    // возврат списка на удаления
    fun getDeleteList(): MutableList<String>? {
        return delateList
    }


    fun cleardealeteList(){
        delateList.clear()
    }



}