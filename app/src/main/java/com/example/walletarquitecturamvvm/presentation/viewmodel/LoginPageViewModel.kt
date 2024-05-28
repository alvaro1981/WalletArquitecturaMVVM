package com.example.walletarquitecturamvvm.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.walletarquitecturamvvm.data.local.ProviderUserModel

class LoginPageViewModel : ViewModel() {

    val checkResponse = MutableLiveData<String>()
    val eMailUserLoged = MutableLiveData<String>()

    fun getLoginEditText(eMail :String, password :String){
        val checkLoginEditTextResponse =  checkloginEditText(eMail, password)
        checkResponse.postValue(checkLoginEditTextResponse)
        if (checkLoginEditTextResponse == "LoginSuccess") {
            eMailUserLoged.postValue(eMail)
        }
    }

    private fun checkloginEditText(mail: String, pass: String):String {

            if(mail.isNotEmpty() && pass.isNotEmpty()){
                if (ProviderUserModel.userExist(mail)) {
                    if( pass == ProviderUserModel.getUser(mail)!!.password){
                        return "LoginSuccess"
                    }
                    return "LA PASSWORD NO CORRESPONDE AL USUARIO"
                }
                return "EL USUARIO NO ESTA REGISTRADO, CREAR CUENTA PORFAVOR"
            }
            return "LLENAR AMBOS CASILLEROS PARA LOGEARSE"
    }
}