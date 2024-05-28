package com.example.walletarquitecturamvvm.presentation.ui.fragments

import android.graphics.Color
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.walletarquitecturamvvm.R
import com.example.walletarquitecturamvvm.data.local.ProviderTransaccionModel
import com.example.walletarquitecturamvvm.data.local.ProviderUserModel
import com.example.walletarquitecturamvvm.databinding.FragmentSendMoneyBinding
import com.example.walletarquitecturamvvm.presentation.viewmodel.SendMoneyViewModel
import com.google.android.material.snackbar.Snackbar

class SendMoney : Fragment() {

val args:SendMoneyArgs by navArgs()

    private val viewModel: SendMoneyViewModel by viewModels()
    private lateinit var binding: FragmentSendMoneyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSendMoneyBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.transactionUserModel.observe(viewLifecycleOwner, Observer {
            val snackbar = Snackbar.make(view,"Transaccion exitosa al usuario ${ viewModel.transactionUserModel.value!!
                .name.toString() } ", 500).setAction("action", null)
            snackbar.setActionTextColor(Color.WHITE)
            val snackbarView = snackbar.view
            snackbarView.setBackgroundColor(Color.BLACK)
            snackbar.show()
            binding.etMoney.text.clear()
            binding.etNotes.text.clear()
        })

        viewModel.rejectedEmail.observe(viewLifecycleOwner, Observer {
           // Log.i("SendMoney","El usuario ${viewModel.rejectedEmail.value} no puede Enviar dinero ")
            val snackbar = Snackbar.make(view,"El usuario ${viewModel.rejectedEmail.value} no puede Enviar dinero ", 500).setAction("action", null)
            snackbar.setActionTextColor(Color.WHITE)
            val snackbarView = snackbar.view
            snackbarView.setBackgroundColor(Color.BLACK)
            snackbar.show()
            navigateToHome(view)
        })

        binding.btnEnviarDinero.setOnClickListener {
            val money = binding.etMoney.text.toString()
            if( money.all { it.isDigit() } && money.isNotEmpty()) {
                viewModel.doSendMoney(money.toDouble(), args.userMail)
            } else {
                binding.etMoney.text.clear()
                binding.etNotes.text.clear()
            }
        }

        binding.ivReturnHome.setOnClickListener {
            navigateToHome(view)
        }
        viewModel.requestMailCheck(args.userMail)
    }
    fun navigateToHome(view: View){
        var userTransactions = ProviderTransaccionModel.getTransaction(
            ProviderUserModel.getUser(args.userMail)!!.userId)

        if(userTransactions != null )
            Navigation.findNavController(view).navigate(SendMoneyDirections.
            actionSendMoneyToHomePage(eMailLoging = args.userMail ))
        else
            Navigation.findNavController(view).navigate(SendMoneyDirections.
            actionSendMoneyToHomePageCaseEmpty(emailSignupPage = args.userMail))

    }


}