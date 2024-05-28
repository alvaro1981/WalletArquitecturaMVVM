package com.example.walletarquitecturamvvm.presentation.ui.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.walletarquitecturamvvm.R
import com.example.walletarquitecturamvvm.databinding.FragmentSignupPageBinding
import com.example.walletarquitecturamvvm.presentation.viewmodel.SignupPageViewModel

class SignupPage : Fragment() {

    private val viewModel: SignupPageViewModel by viewModels()
    lateinit var binding: FragmentSignupPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignupPageBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.allOk.observe(viewLifecycleOwner, Observer {
            Log.i("SignupPageFragment",viewModel.allOk.value.toString())
            //Toast.makeText(activity,viewModel.allOk.value.toString(),Toast.LENGTH_SHORT).show();

        })

        viewModel.emailUserLoginSuccess.observe(viewLifecycleOwner, Observer {
                Navigation.findNavController(view).navigate(SignupPageDirections.
                actionSignupPageToHomePage(eMailLoging = viewModel.emailUserLoginSuccess.value.toString()))

        })
        binding.btnCreaCtaSignup.setOnClickListener {
          viewModel.getSignUpTextViews(binding.etNombre.text.toString(),
                                       binding.etApellido.text.toString(),
                                       binding.etEmail.text.toString(),
                                       binding.etPassword.text.toString(),
                                       binding.etReingresaPassword.text.toString());

        }
        binding.tvGoLogin.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_signupPage_to_loginPage)
        }
    }



}