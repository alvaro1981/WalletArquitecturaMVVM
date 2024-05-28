package com.example.walletarquitecturamvvm.data.model

import android.service.restrictions.RestrictionsReceiver

data class TransactionModel (val transactionId:Int,
                             val amount : Double,
                             val transactionDate:String,
                             val accountId:Int ,
                             val to_accountId:Int,
                             val type:String,
                             val userId :Int)




