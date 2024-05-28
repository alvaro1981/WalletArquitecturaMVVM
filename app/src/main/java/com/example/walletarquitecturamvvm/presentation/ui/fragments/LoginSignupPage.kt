package com.example.walletarquitecturamvvm.presentation.ui.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.walletarquitecturamvvm.R
import com.example.walletarquitecturamvvm.databinding.FragmentLoginSignupPageBinding
import com.example.walletarquitecturamvvm.databinding.FragmentSignupPageBinding
import com.example.walletarquitecturamvvm.presentation.viewmodel.LoginSignupPageViewModel

class LoginSignupPage : Fragment() {

    private lateinit var binding: FragmentLoginSignupPageBinding
    private val viewModel: LoginSignupPageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // crear la instancia de la clase binding para este fragmento
        binding = FragmentLoginSignupPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.login.observe(viewLifecycleOwner, Observer {
            Navigation.findNavController(view).navigate(R.id.action_loginSignupPage_to_loginPage)
        })

        viewModel.signUp.observe(viewLifecycleOwner, Observer {
            Navigation.findNavController(view).navigate(R.id.action_loginSignupPage_to_signupPage)
        })

        binding.tvIrLogin.setOnClickListener{
            viewModel.goLogin()
        }

        binding.btnCreaCuenta.setOnClickListener {
            viewModel.goSignup()
        }
    }
}