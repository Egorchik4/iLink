package com.example.myapplication.screens.menu

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.activityViewModels
import com.example.myapplication.R
import com.example.myapplication.databinding.MenuFragmentBinding
import com.example.myapplication.screens.photos.PhotoFragment
import com.example.myapplication.screens.photos.PhotoViewModel
import java.io.FileOutputStream
import java.io.OutputStream
import com.example.myapplication.data.FileSave
import java.io.File

class MenuFragment : Fragment() {


    lateinit var binding: MenuFragmentBinding  // название разметки layout + 'binding'
    private val VMMenu: MenuViewModel by activityViewModels()  // класс ViewModel
    private val VMPhoto: PhotoViewModel by activityViewModels()

    private var i : Int = 0
    private var n: Int = 0

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
        VMMenu.Live.observe(viewLifecycleOwner) {
            // инициализация объекта
            val buttonone = binding.buttoncat as TextView
            val buttontwo = binding.buttonduck as TextView
            var params = buttonone.layoutParams as ViewGroup.MarginLayoutParams
            var paramstwo = buttontwo.layoutParams as ViewGroup.MarginLayoutParams

            // взятие значений из livedata
            params.topMargin = VMMenu.Live.value!!
            paramstwo.topMargin = VMMenu.Live.value!!
        }


        VMPhoto.LiveInt.observe(viewLifecycleOwner){
            n = VMPhoto.LiveInt.value!!  // работает елси не null
        }

        binding.buttonduck.setOnClickListener {
            VMMenu.animation(binding.buttoncat,binding.buttonduck,1100)
            VMMenu.showdug(binding.imageView)
            binding.frameLayout.visibility = View.VISIBLE
            if (binding.frameLayout.visibility.equals(View.VISIBLE)){
                binding.buttonlike.visibility = View.VISIBLE // показ кнопки Like
            }


        }
        binding.buttoncat.setOnClickListener {
            VMMenu.animation(binding.buttoncat,binding.buttonduck,1100)
            VMMenu.showcat(binding.imageView)
            binding.buttonlike.visibility = View.VISIBLE
        }


        binding.buttonlike.setOnClickListener {
            val text: String = "Like and Save"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(binding.buttonlike.context, text, duration)
            toast.show()

            // загрузка изображения
            var path: String = VMMenu.saveFile(binding.imageView,n)   // получаем путь к файлу
            VMPhoto.savepath(path) // отправляем путь на сохранение
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

                    // загрузка изображения
                    var path: String = VMMenu.saveFile(binding.imageView,n)   // получаем путь к файлу
                    VMPhoto.savepath(path) // отправляем путь на сохранение

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