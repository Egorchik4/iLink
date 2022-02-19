package com.example.myapplication.screens.photos

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.PhotoFragmentBinding
import com.example.myapplication.screens.menu.MenuFragment

class PhotoFragment : Fragment() {

    lateinit var binding: PhotoFragmentBinding  // название разметки layout + 'binding'
    private val VMPhotone: PhotoViewModel by activityViewModels()
    //private val VMMenuone: MenuViewModel by activityViewModels()  // класс ViewModel
    //lateinit var data: MutableList<String>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = PhotoFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // настройка RecyclerAdapter
        val adapter = RecyclerAdapter()
        binding.rcView.layoutManager = GridLayoutManager(activity,3)  // GridLayoutManager: упорядочивает элементы в виде списка с кол. колонок
        binding.rcView.adapter = adapter


        // для добавления новых элемнетов списка
        VMPhotone.addLive.observe(viewLifecycleOwner){
            Log.e("eee", "addLive")
            adapter.addAll(VMPhotone.addLive.value)
        }

        // для удаления элементов
        VMPhotone.deleteLive.observe(viewLifecycleOwner){
            Log.e("eee", "deleteLive")
            adapter.deleteALL(VMPhotone.deleteLive.value)
        }


        // добавление элемента
        //VMPhotone.addLive.observe(viewLifecycleOwner){
         //   adapter.addItem(VMPhotone.addLive.value!!)
        //}



        VMPhotone.RecLive.observe(viewLifecycleOwner){
            adapter.check(VMPhotone.RecLive.value ?: 0)
        }


        binding.bReturn.setOnClickListener {
            fragmentManager
                ?.beginTransaction()
                ?.replace(R.id.fragment_container,MenuFragment())
                ?.commit()
        }

        binding.bDelate.setOnClickListener {
            if (VMPhotone.RecLive.value == 0){
                VMPhotone.saveInt(1,1) // активация галочек
                binding.bDelate.setBackgroundColor(ContextCompat.getColor(binding.bDelate.context,R.color.red))
                binding.bDelateALL.visibility = View.GONE
                binding.bReturn.visibility = View.GONE
            }else{
                VMPhotone.saveInt(0,1) // дезактивация
                binding.bDelate.setBackgroundColor(ContextCompat.getColor(binding.bDelate.context,R.color.purple_500))
                binding.bDelateALL.visibility = View.VISIBLE
                binding.bReturn.visibility = View.VISIBLE

                // через LiveData
                VMPhotone.DeleteList(adapter.getDeleteList()!!) // передаём список индексов которые нужно удалить
                adapter.cleardealeteList() // очистка листа удаления

            }

        }

        binding.bDelateALL.setOnClickListener {
            // очитска памяти!!!
            //VMPhotone.delateAL()
            VMPhotone.saveInt(0,0)
        }
    }
}