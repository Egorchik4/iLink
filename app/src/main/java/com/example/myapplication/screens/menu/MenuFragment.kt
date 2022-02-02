package com.example.myapplication.screens.menu

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.myapplication.R
import com.example.myapplication.databinding.MenuFragmentBinding
import com.example.myapplication.screens.photos.PhotoFragment

class MenuFragment : Fragment() {

    lateinit var binding: MenuFragmentBinding  // название разметки layout + 'binding'
    private val ViewMod: MenuViewModel by activityViewModels()  // класс ViewModel

    private var i : Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = MenuFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // назавание на кнопках
        binding.buttoncat.text = "CAT"
        binding.buttonduck.text = "DUCK"

        binding.buttonlike.text = "Like"
        binding.bSpisok.text = "List"



        // наблюдатели
        ViewMod.Live.observe(viewLifecycleOwner) {
            // инициализация объекта
            val buttonone = binding.buttoncat as TextView
            val buttontwo = binding.buttonduck as TextView

            var params = buttonone.layoutParams as ViewGroup.MarginLayoutParams
            var paramstwo = buttontwo.layoutParams as ViewGroup.MarginLayoutParams


            // взятие значений из livedata
            params.topMargin = ViewMod.Live.value!!
            paramstwo.topMargin = ViewMod.Live.value!!
        }



        binding.buttonduck.setOnClickListener {
            ViewMod.animation(binding.buttoncat,binding.buttonduck,1100)
            ViewMod.showdug(binding.imageView)
            binding.buttonlike.visibility = View.VISIBLE // показ кнопки Like


        }
        binding.buttoncat.setOnClickListener {
            ViewMod.animation(binding.buttoncat,binding.buttonduck,1100)
            ViewMod.showcat(binding.imageView)
            binding.buttonlike.visibility = View.VISIBLE
        }


        binding.buttonlike.setOnClickListener {
            val text : String = "Like and Save"
            val duration = Toast.LENGTH_SHORT

            val toast = Toast.makeText(binding.buttonlike.context, text, duration)
            toast.show()

        }



        binding.imageView.setOnClickListener {
            i++
            val handler = Handler()

            handler.postDelayed({
                if(i == 2){
                    val text = "Like and Save"
                    val duration = Toast.LENGTH_SHORT

                    val toast = Toast.makeText(binding.buttonlike.context, text, duration)
                    toast.show()

                }
                i = 0
            },400)

        }

        binding.bSpisok.setOnClickListener {
                fragmentManager
                    ?.beginTransaction()
                    ?.replace(R.id.fragment_container,PhotoFragment())
                    ?.commit()
            }
        }

}