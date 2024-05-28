package com.example.walletarquitecturamvvm.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.walletarquitecturamvvm.data.local.ProviderAccountModel
import com.example.walletarquitecturamvvm.data.local.ProviderUserModel
import com.example.walletarquitecturamvvm.data.model.AccountModel
import com.example.walletarquitecturamvvm.data.model.UserModel

class HomePageCaseEmptyViewModel : ViewModel() {

    val userHomeCaseEmpty = MutableLiveData<UserModel?>()
    val userAccountCaseEmpty = MutableLiveData<AccountModel>()

    fun getDataHomeEmptyCase(createdUserEmail:String){
        val userModel = ProviderUserModel.getUser(createdUserEmail)
        userHomeCaseEmpty.postValue(userModel)
        userAccountCaseEmpty.postValue(ProviderAccountModel.getAccount(userModel!!.userId))
    }

}