package com.example.walletarquitecturamvvm.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginSignupPageViewModel : ViewModel() {

    val login  = MutableLiveData<Boolean>()
    val signUp = MutableLiveData<Boolean>()

    fun goLogin(){
        login.value = login.value != true
    }
    fun goSignup(){
        signUp.value = signUp.value != true
    }

}