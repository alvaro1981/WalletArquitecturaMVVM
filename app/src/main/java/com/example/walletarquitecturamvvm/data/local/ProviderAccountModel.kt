package com.example.walletarquitecturamvvm.data.local

import com.example.walletarquitecturamvvm.data.model.AccountModel
import com.example.walletarquitecturamvvm.data.model.UserModel

/**
 * Clase encargada de administrar las cuentas de los usuarios , agrega usuarios a la listaCuentas
 */
class ProviderAccountModel {
  companion object{
      /**
       * agrega cuentas a la mutablelist de modelo de cuentas   AccountModel
       *
       */
    fun addUser(newAccount: AccountModel):Int{
        val modAccount = newAccount.copy(accountId = (listaCuentas.size + 1))
        listaCuentas.add(modAccount)
        return modAccount.accountId
    }

      /**
       * retorna una cuenta especificando el user id del AccountModel en la mutable list
       */
    fun getAccount(idUser:Int): AccountModel? {
        for(accountModel in listaCuentas){
            if(accountModel.userId == idUser) return accountModel
        }
        return null
    }

    private val listaCuentas = mutableListOf<AccountModel>(
        AccountModel(1,1500000.0,"2022-03-03",1),
        AccountModel(2,2000000.0,"2022-05-18",2),
        AccountModel(3,1500000.0,"2023-08-09",3),
        AccountModel(4,2500000.0,"2023-10-01",4),
        AccountModel(5,3400000.0,"2024-01-02",5),
        AccountModel(6,2200000.0,"2024-01-15",6),
    )
  }
}