package com.example.myapplication.screens.photos

import android.graphics.Color
import android.os.Bundle
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

        binding.bReturn.text = "Go back"
        binding.bDelateALL.text = "DealateALL"
        binding.bDelate.text = "delate"

        // добаваление RecyclerView
      //  val adapter = RecyclerAdapter(object : MyOnClick{
           // override fun onClicked(tag: String) {
            //    val text: String = tag
            //    val duration = Toast.LENGTH_SHORT
           //     val toast = Toast.makeText(binding.rcView.context, text, duration)
           //     toast.show()
          //  }

        //})  // создание объекта класса RecyclerAdapter
        //VMPhotone.LiveInt.observe(viewLifecycleOwner){

        //}

        val adapter = RecyclerAdapter()
        binding.rcView.layoutManager = GridLayoutManager(activity,3)  // GridLayoutManager: упорядочивает элементы в виде списка с кол. колонок
        binding.rcView.adapter = adapter


        VMPhotone.SLive.observe(viewLifecycleOwner){
            adapter.addAll(VMPhotone.SLive.value)  // отображение списка   // через observe!!!
        }

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

                // черех LiveData
                VMPhotone.delateById(adapter.getDeleteList()!!) // передаём список индексов которые нужно удалить
                adapter.cleardealeteList() // очистка листа удаления

            }

        }

        binding.bDelateALL.setOnClickListener {
            // очитска памяти!!!
            VMPhotone.delateAL()
            VMPhotone.saveInt(0,0)
        }
    }
}