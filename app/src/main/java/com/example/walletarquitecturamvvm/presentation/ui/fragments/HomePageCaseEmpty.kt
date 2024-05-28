package com.example.walletarquitecturamvvm.presentation.ui.fragments

import android.annotation.SuppressLint
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.walletarquitecturamvvm.R
import com.example.walletarquitecturamvvm.databinding.FragmentHomePageCaseEmptyBinding
import com.example.walletarquitecturamvvm.presentation.viewmodel.HomePageCaseEmptyViewModel

class HomePageCaseEmpty : Fragment() {

    val args:HomePageCaseEmptyArgs by navArgs()
    private val viewModel: HomePageCaseEmptyViewModel by viewModels()
    private lateinit var binding: FragmentHomePageCaseEmptyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomePageCaseEmptyBinding.inflate(inflater,container,false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.userHomeCaseEmpty.observe(viewLifecycleOwner, Observer {

            binding.tvBienvenida.text = " Hola, ${viewModel.userHomeCaseEmpty.value!!.name} !"
            binding.profilePhoto.setImageResource(viewModel.userHomeCaseEmpty.value!!.profilePicture)

        })
        viewModel.userAccountCaseEmpty.observe(viewLifecycleOwner, Observer {
            binding.tvSaldo.text = viewModel.userAccountCaseEmpty.value?.saldo.toString()
        })
        viewModel.getDataHomeEmptyCase(args.emailSignupPage)

        binding.tvInicio.setOnClickListener {
            Navigation.findNavController(view).
            navigate(R.id.action_homePageCaseEmpty_to_loginSignupPage)
        }
        binding.btnIngresarDinero.setOnClickListener {

            Navigation.findNavController(view).navigate(HomePageCaseEmptyDirections.
            actionHomePageCaseEmptyToRequestMoney(userMail = args.emailSignupPage))
        }

        binding.btnEnviarDinero.setOnClickListener {

            Navigation.findNavController(view).navigate(HomePageCaseEmptyDirections.
            actionHomePageCaseEmptyToSendMoney(userMail = args.emailSignupPage))
        }

    }



}