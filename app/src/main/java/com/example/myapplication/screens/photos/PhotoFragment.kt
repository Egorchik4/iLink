package com.example.myapplication.screens.photos

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.PhotoFragmentBinding
import com.example.myapplication.screens.menu.MenuFragment
import com.example.myapplication.screens.menu.MenuViewModel

class PhotoFragment : Fragment() {

    lateinit var binding: PhotoFragmentBinding  // название разметки layout + 'binding'
    private val VMPhotone: PhotoViewModel by activityViewModels()
    //private val VMMenuone: MenuViewModel by activityViewModels()  // класс ViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = PhotoFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bReturn.text = "Go back"
        binding.bDelateALL.text = "DealateALL"

        // добаваление RecyclerView
        val adapter = RecyclerAdapter()  // создание объекта класса RecyclerAdapter
        binding.rcView.layoutManager = GridLayoutManager(activity,3)  // GridLayoutManager: упорядочивает элементы в виде списка с кол. колонок
        binding.rcView.adapter = adapter

        VMPhotone.SLive.observe(viewLifecycleOwner){
            adapter.addAll(VMPhotone.SLive.value)  // отображение списка   // через observe!!!
        }


        binding.bReturn.setOnClickListener {
            fragmentManager
                ?.beginTransaction()
                ?.replace(R.id.fragment_container,MenuFragment())
                ?.commit()
        }

        binding.bDelateALL.setOnClickListener {
            // очитска памяти!!!
            VMPhotone.delateAL()
            VMPhotone.saveInt(0)
        }
    }
}