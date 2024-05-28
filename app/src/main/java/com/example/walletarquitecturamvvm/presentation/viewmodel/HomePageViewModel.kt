package com.example.walletarquitecturamvvm.presentation.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.walletarquitecturamvvm.data.local.ProviderAccountModel
import com.example.walletarquitecturamvvm.data.local.ProviderTransaccionModel
import com.example.walletarquitecturamvvm.data.local.ProviderUserModel
import com.example.walletarquitecturamvvm.data.model.AccountModel
import com.example.walletarquitecturamvvm.data.model.TransactionModel
import com.example.walletarquitecturamvvm.data.model.UserModel

class HomePageViewModel : ViewModel() {
    val userHome = MutableLiveData<UserModel>()
    val userAccount = MutableLiveData<AccountModel>()
    val listaTransacciones = MutableLiveData<List<TransactionModel>?>()
    val goEmptyHomePage = MutableLiveData<String>()
    @SuppressLint("SuspiciousIndentation")
    fun getDataHome(logedUserMail:String){
        val userModel = ProviderUserModel.getUser(logedUserMail)

        userHome.postValue(userModel!!)
        userAccount.postValue(ProviderAccountModel.getAccount(userModel.userId))
        val transactionModel = ProviderTransaccionModel.
        getTransaction(ProviderUserModel.getUser(logedUserMail)!!.userId)

            if(transactionModel != null) {

                listaTransacciones.postValue(transactionModel)

            }else  goEmptyHomePage.postValue(logedUserMail)
    }


}