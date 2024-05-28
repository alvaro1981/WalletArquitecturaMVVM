package com.example.walletarquitecturamvvm.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SplashScreenViewModel : ViewModel() {

    val next = MutableLiveData<Boolean>()


    fun changeNext(){
        next.value = next.value != true
    }

}