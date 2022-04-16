package com.watasolutions.file.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.watasolutions.file.AppData
import com.watasolutions.file.databinding.FragmentHomeBinding
import com.watasolutions.file.viewmodels.ViewModelFactory


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    lateinit var vm: HomeVM
    lateinit var binding: FragmentHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = ViewModelProvider(
            this,
            ViewModelFactory(context?.applicationContext as AppData)
        )[HomeVM::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button4.setOnClickListener {
            val username = binding.tvUsername.editText?.text.toString().trim()
            val pass = binding.tvPassword.editText?.text.toString().trim()
            val lastName = binding.tvLastname.editText?.text.toString().trim()
            val firstName = binding.tvFirstname.editText?.text.toString().trim()
            vm.insertUser(
                firstName = firstName,
                lastName = lastName,
                username = username,
                pass = pass
            )
        }
        registerEventSuccess()
    }

    private fun registerEventSuccess() {
        vm.registerSuccessEvent.observe(this){isInsertSuccess ->
            when(isInsertSuccess){
                true -> {
                    binding.tvUsername.editText?.setText("")
                    binding.tvPassword.editText?.setText("")
                    binding.tvLastname.editText?.setText("")
                    binding.tvFirstname.editText?.setText("")
                    Toast.makeText(context, "register success", Toast.LENGTH_SHORT).show()
                }
                false -> {
                    Toast.makeText(context, "somethings is wrong", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }


}