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
            Log.e("eee", "deleteLive "+VMPhotone.deleteLive.value.toString())
            adapter.deleteALL(VMPhotone.deleteLive.value)
        }
        
        // появление галочек
        VMPhotone.RecLive.observe(viewLifecycleOwner){
            adapter.check(VMPhotone.RecLive.value ?: 0)
        }

        // выделение всех элементов списка
        VMPhotone.SelectLive.observe(viewLifecycleOwner){
            adapter.activatecheck(VMPhotone.SelectLive.value ?: 0)
        }

        
        binding.bReturn.setOnClickListener {
            fragmentManager
                ?.beginTransaction()
                ?.setCustomAnimations(R.anim.enter_left_to_right,R.anim.exit_left_to_right)
                ?.replace(R.id.fragment_container,MenuFragment())
                ?.commit()
        }

        binding.bDelate.setOnClickListener {
            if (VMPhotone.RecLive.value == 0){
                VMPhotone.saveInt(1,1) // активация галочек
                //binding.bDelate.setBackgroundColor(ContextCompat.getColor(binding.bDelate.context,R.color.red))
                binding.bSelectALL.visibility = View.VISIBLE
                binding.bReturn.visibility = View.GONE
                VMPhotone.selectInt(0)
            }else{
                VMPhotone.saveInt(0,1) // дезактивация
                //binding.bDelate.setBackgroundColor(ContextCompat.getColor(binding.bDelate.context,R.color.purple_500))
                binding.bSelectALL.visibility = View.GONE
                binding.bReturn.visibility = View.VISIBLE

                // через LiveData
                VMPhotone.DeleteList(adapter.getDeleteList()!!) // передаём список индексов которые нужно удалить
                adapter.cleardealeteList() // очистка листа удаления
            }
        }

        binding.bSelectALL.setOnClickListener {
            // выделение всех объектов
            if (VMPhotone.SelectLive.value == 0){
                VMPhotone.selectInt(1)  // выделение всех объектов в списке
            }else{
                VMPhotone.selectInt(0)  // снятие выделения
            }
        }
    }
}