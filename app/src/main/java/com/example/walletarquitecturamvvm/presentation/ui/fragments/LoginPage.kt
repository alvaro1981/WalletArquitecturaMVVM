package com.example.walletarquitecturamvvm.presentation.ui.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.walletarquitecturamvvm.R
import com.example.walletarquitecturamvvm.databinding.FragmentLoginPageBinding
import com.example.walletarquitecturamvvm.presentation.viewmodel.LoginPageViewModel

class LoginPage : Fragment() {
    private lateinit var binding: FragmentLoginPageBinding
    private val viewModel: LoginPageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginPageBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.checkResponse.observe(viewLifecycleOwner, Observer {
           Log.i("LoginPageFragment",viewModel.checkResponse.value.toString())
            if(viewModel.checkResponse.value.toString() == "LoginSuccess"){
              //  Navigation.findNavController(view).navigate(R.id.action_loginPage_to_homePage)

            }
        })
        viewModel.eMailUserLoged .observe(viewLifecycleOwner, Observer {
            Log.i("LoginPageFragment"," Email Usuario " +
                    "Logeado = ${viewModel.eMailUserLoged.value.toString()}")
            Navigation.findNavController(view).navigate(LoginPageDirections.
            actionLoginPageToHomePage(eMailLoging = viewModel.eMailUserLoged.value.toString()))
        })

        binding.btnLoginPage.setOnClickListener {
            viewModel.getLoginEditText(binding.tvEmail.text.toString(),
                                       binding.etIngresaPassword.text.toString())

        }

        binding.tvCrearCuenta.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_loginPage_to_signupPage)
        }
    }
}