package com.example.walletarquitecturamvvm.presentation.ui.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.walletarquitecturamvvm.R
import com.example.walletarquitecturamvvm.databinding.FragmentSplashScreenBinding
import com.example.walletarquitecturamvvm.presentation.viewmodel.SplashScreenViewModel

class SplashScreenFragment : Fragment() {

    private val viewModel: SplashScreenViewModel by viewModels()
    private lateinit var binding: FragmentSplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       //crea instancia clase binding para este fragmento
        binding = FragmentSplashScreenBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.next.observe(viewLifecycleOwner) {
            Navigation.findNavController(view)
                .navigate(R.id.action_splashScreenFragment_to_loginSignupPage)
        }
        binding.splashContainer.setOnClickListener {
             viewModel.changeNext()
        }

    }
}