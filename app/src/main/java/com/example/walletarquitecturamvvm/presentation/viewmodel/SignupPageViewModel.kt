package com.example.walletarquitecturamvvm.presentation.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.walletarquitecturamvvm.R
import com.example.walletarquitecturamvvm.data.local.ProviderAccountModel
import com.example.walletarquitecturamvvm.data.local.ProviderUserModel
import com.example.walletarquitecturamvvm.data.model.AccountModel
import com.example.walletarquitecturamvvm.data.model.UserModel
import java.time.LocalDate
import java.util.Calendar

class SignupPageViewModel : ViewModel() {


    val allOk  = MutableLiveData<String>()
    val emailUserLoginSuccess = MutableLiveData<String>()
    fun getSignUpTextViews(name       :String,
                           lastName   :String,
                           eMail      :String,
                           password   :String,
                           againPassw :String ){

        Log.i("SignupPageFragment","name = ${name.length}, lastname: ${lastName.length}," +
                "email: ${eMail.length} , password : ${password.length} , reingresarPasswor : ${againPassw.length}")
        val checkTextViewResponse =  checkTextview(name, lastName, eMail, password, againPassw)
        allOk.postValue(checkTextViewResponse)
        if (checkTextViewResponse == "OK") {
            val idUserAccount = ProviderUserModel.addUser(UserModel(0,name,lastName,
                             eMail,password, R.drawable.anonymous))
             ProviderAccountModel.addUser(AccountModel(0,1200000.0,
                 Calendar.getInstance().time.toString(),idUserAccount))
            Log.i("SignupPageFragment","UserModel:  ${ProviderUserModel.getUser(eMail)}")
            Log.i("SignupPageFragment","AccountModel: ${ProviderAccountModel.getAccount(idUserAccount)}")
        }

    }


    fun checkTextview(name :String, lastName  :String,
                      eMail:String, password  :String,
                      againPassw :String ): String {

          if(name.isNotEmpty() && lastName.isNotEmpty() && eMail.isNotEmpty()
              && password.isNotEmpty() && againPassw.isNotEmpty()){
              if(password == againPassw){
                   if (ProviderUserModel.userExist(eMail)) return "YA EXISTE UN USUARIO CON EL MAIL $eMail"
                   else {
                         emailUserLoginSuccess.postValue(eMail)
                         return "OK"
                   }
              }else return "NO SON IGUALES LAS PASSWORD"
          }
           return "NO DEBEN HABER CAMPOS VACIOS"
    }

}