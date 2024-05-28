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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.walletarquitecturamvvm.R
import com.example.walletarquitecturamvvm.data.model.TransactionModel
import com.example.walletarquitecturamvvm.databinding.FragmentHomePageBinding
import com.example.walletarquitecturamvvm.presentation.viewmodel.HomePageViewModel
import java.text.DecimalFormat

class HomePage : Fragment() {
val args:HomePageArgs by navArgs()


    private val viewModel: HomePageViewModel by viewModels()
    private lateinit var  binding: FragmentHomePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomePageBinding.inflate(inflater,container,false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val userLoged = args.eMailLoging
        val dec = DecimalFormat("#,000")
        viewModel.goEmptyHomePage.observe(viewLifecycleOwner, Observer {

        })
        viewModel.userHome.observe(viewLifecycleOwner, Observer {
           binding.tvholaUsuario.text = " Hola, ${viewModel.userHome.value!!.name} !"
           binding.profilePhoto.setImageResource(viewModel.userHome.value!!.profilePicture)
        })

        viewModel.userAccount.observe(viewLifecycleOwner, Observer {
         //binding.tvSaldo.text = viewModel.userAccount.value!!.saldo.toString()
         binding.tvSaldo.text = dec.format(viewModel.userAccount.value!!.saldo)

        })

        viewModel.listaTransacciones.observe(viewLifecycleOwner, Observer {

            initRecycler(viewModel.listaTransacciones.value!!.reversed())
        })

         viewModel.getDataHome(args.eMailLoging)

        binding.tvInicio.setOnClickListener {
          Navigation.findNavController(view).navigate(R.id.action_homePage_to_loginSignupPage)
        }

        binding.btnIngresarDinero.setOnClickListener {

            Navigation.findNavController(view).navigate(HomePageDirections.
            actionHomePageToRequestMoney(userMail = args.eMailLoging))
        }

        binding.btnEnviarDinero.setOnClickListener {
            Navigation.findNavController(view).navigate(HomePageDirections.
            actionHomePageToSendMoney(userMail = args.eMailLoging))
        }



    }

    fun initRecycler(transacciones:List<TransactionModel>){
        binding.rvhomepage.layoutManager = LinearLayoutManager(activity)
        val transactionAdaptador = TransactionAdapter(transacciones)
        binding.rvhomepage.adapter = transactionAdaptador
    }
}