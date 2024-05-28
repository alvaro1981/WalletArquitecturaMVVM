package com.example.walletarquitecturamvvm.data.local

import com.example.walletarquitecturamvvm.R
import com.example.walletarquitecturamvvm.data.model.UserModel

class ProviderUserModel {
  companion object{
    fun userExist(emailUser:String):Boolean{
        for(userModel in userList){
            if(userModel.email == emailUser) return true
        }
        return false
    }
    fun getUser(emailUser: String): UserModel? {
        for(userModel in userList){
            if(userModel.email == emailUser) return userModel
        }
        return null
    }

      fun getUserForId(Id: Int): UserModel? {
          for(userModel in userList){
              if(userModel.userId == Id) return userModel
          }
          return null
      }
    fun addUser(newUser:UserModel):Int{
        val modUser = newUser.copy(userId = (userList.size + 1))
        userList.add(modUser)
        return modUser.userId
    }



    private val userList = mutableListOf<UserModel>(
        UserModel(1,"Amanda","Alkemy","amanda@alkemy.com","amanda", R.drawable.profile_picture),
        UserModel(2,"Sara", "Ibrahim","sara@alkemy.com","sara",R.drawable.profile_sara),
        UserModel(3,"Ahmad", "Ibrahim","ahmad@alkemy.com","ahmad",R.drawable.profile_ahmad),
        UserModel(4,"Reem", "Khaled","reem@alkemy.com", "reem",R.drawable.profile_reem),
        UserModel(5,"Hiba", "Saleh","hiba@alkemy.com","hiba",R.drawable.profile_hiba),
        UserModel(6,"Yara","Khalil","yara@alkemy.com","yara",R.drawable.profile_yara)
    )
  }
}