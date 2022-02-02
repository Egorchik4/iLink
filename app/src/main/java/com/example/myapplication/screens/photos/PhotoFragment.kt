package com.example.myapplication.screens.photos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.data.Data
import com.example.myapplication.databinding.PhotoFragmentBinding
import com.example.myapplication.screens.menu.MenuFragment

class PhotoFragment : Fragment() {

    lateinit var binding: PhotoFragmentBinding  // название разметки layout + 'binding'
    private val viewModel: PhotoViewModel by activityViewModels()

    val photoList = Data()  // объект класса Data
    var list: MutableList<String> = photoList.productSet

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = PhotoFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bReturn.text = "Go back"

        // добаваление списка
        val adapter = RecyclerAdapter()  // создание объекта класса RecyclerAdapter

        binding.rcView.layoutManager = LinearLayoutManager(activity)  // LinearLayoutManager: упорядочивает элементы в виде списка с одной колонкой
        binding.rcView.adapter = adapter

        adapter.addAll(list)  // отображение начального списка


        binding.bReturn.setOnClickListener {
            fragmentManager
                ?.beginTransaction()
                ?.replace(R.id.fragment_container,MenuFragment())
                ?.commit()
        }

    }

}