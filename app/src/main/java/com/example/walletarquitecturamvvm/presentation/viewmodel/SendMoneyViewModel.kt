package com.example.walletarquitecturamvvm.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.walletarquitecturamvvm.data.local.ProviderAccountModel
import com.example.walletarquitecturamvvm.data.local.ProviderTransaccionModel
import com.example.walletarquitecturamvvm.data.local.ProviderUserModel
import com.example.walletarquitecturamvvm.data.model.TransactionModel
import com.example.walletarquitecturamvvm.data.model.UserModel
import java.util.Calendar

class SendMoneyViewModel : ViewModel() {
    val rejectedEmail = MutableLiveData<String>()
    val transactionUserModel = MutableLiveData<UserModel?>()
    private val mailYara = "yara@alkemy.com"
    val yaraAccountId = ProviderUserModel.getUser(mailYara)!!.userId
    val accountYara= ProviderAccountModel.getAccount(yaraAccountId)

    fun requestMailCheck(userMail:String){
        if(userMail == mailYara){
            rejectedEmail.postValue(userMail)
        }
    }

    fun doSendMoney(money: Double, userMail: String) {
        val userModel = ProviderUserModel.getUser(userMail)
        val userId = userModel!!.userId
        val userAccountModel = ProviderAccountModel.getAccount(userId)
        userAccountModel!!.saldo -= money
        accountYara!!.saldo += money
        val transactionTimeStamp = Calendar.getInstance().time.toString()
        val userTransaction = TransactionModel(0,money, transactionTimeStamp,
            userId,yaraAccountId,"payment",userId)
        ProviderTransaccionModel.addTransaction(userTransaction)
        val yaraTransaction = TransactionModel(0,money, transactionTimeStamp,
            yaraAccountId,userId,"topup",yaraAccountId)
        ProviderTransaccionModel.addTransaction(yaraTransaction)
        transactionUserModel.postValue(userModel)
    }
}