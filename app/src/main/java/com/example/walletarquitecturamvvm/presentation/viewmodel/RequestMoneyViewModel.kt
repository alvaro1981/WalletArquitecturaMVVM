package com.example.walletarquitecturamvvm.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.walletarquitecturamvvm.data.local.ProviderAccountModel
import com.example.walletarquitecturamvvm.data.local.ProviderTransaccionModel
import com.example.walletarquitecturamvvm.data.local.ProviderUserModel
import com.example.walletarquitecturamvvm.data.model.TransactionModel
import com.example.walletarquitecturamvvm.data.model.UserModel
import java.util.Calendar

class RequestMoneyViewModel : ViewModel() {

    val rejectedEmail = MutableLiveData<String>()
    val transactionUserModel = MutableLiveData<UserModel?>()
    private val mailReem = "reem@alkemy.com"
    val reemAccountId = ProviderUserModel.getUser(mailReem)!!.userId
    val accountReem = ProviderAccountModel.getAccount(reemAccountId)

    fun requestMailCheck(userMail:String){
            if(userMail == mailReem){
               rejectedEmail.postValue(userMail)
            }
    }

     fun doRequestMoney(money: Double, userMail: String) {
          val userModel = ProviderUserModel.getUser(userMail)
          val userId = userModel!!.userId
          val userAccountModel =ProviderAccountModel.getAccount(userId)
          userAccountModel!!.saldo += money
          accountReem!!.saldo -= money
          val transactionTimeStamp = Calendar.getInstance().time.toString()
          val userTransaction = TransactionModel(0,money, transactionTimeStamp,
              userId,reemAccountId,"topup",userId)
          ProviderTransaccionModel.addTransaction(userTransaction)
          val reemTransaction = TransactionModel(0,money, transactionTimeStamp,
              reemAccountId,userId,"payment",reemAccountId)
          ProviderTransaccionModel.addTransaction(reemTransaction)
          transactionUserModel.postValue(userModel)
    }



}