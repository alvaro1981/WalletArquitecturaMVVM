package com.example.walletarquitecturamvvm.data.local

import com.example.walletarquitecturamvvm.data.model.TransactionModel

/**
 * clase encargada de administrar las transacciones de todos las cuentas de los usuarios
 */
class ProviderTransaccionModel {
  companion object{
      /**
       *  funcion que permite agregar nuevas transacciones a la mutablelist de Transacionmodel
       */
    fun addTransaction(newTransaction: TransactionModel):Int{
        val modTransaction = newTransaction.copy(transactionId = (transactionList.size + 1))
        transactionList.add(modTransaction)
        return modTransaction.transactionId
    }

      /**
       * funcion encargada de retornar una lista de transacciones especificando del id del usuario
       */
    fun getTransaction(userIdentification: Int): List<TransactionModel>? {
        val  userTransactions = mutableListOf<TransactionModel>()
        for(accountModel in transactionList){
            if(accountModel.userId == userIdentification) {
                if (accountModel.type == "payment") userTransactions.add(accountModel.copy(amount = -accountModel.amount))
                else userTransactions.add(accountModel)
            }
        }
        if (userTransactions.isEmpty()) return null

        return userTransactions
    }

    private val transactionList = mutableListOf<TransactionModel>(
        TransactionModel(1,50300.0,"Dec 12, 10:21 AM",1,2,"topup",1),
        TransactionModel(2,10000.0,"Jan 22, 01:03 PM",1,3,"topup",1),
        TransactionModel(3,35000.0,"Feb 14, 12:45 AM",1,4,"payment",1),
        TransactionModel(4,122500.0,"Mar 15, 01:41 AM",1,5,"topup",1),
        TransactionModel(5,103500.0,"Apr 14, 06:57 PM",1,6,"topup",1),
        TransactionModel(6,370000.0,"Dec 12, 11:20 AM",2,1,"payment",2),
        TransactionModel(7,17000.0,"Jan 22, 01:51 AM",2,3,"topup",2),
        TransactionModel(8,11000.0,"Feb 11, 01:27 PM",2,4,"payment",2),
        TransactionModel(9,70000.0,"Apr 01, 09:34 PM",2,5,"topup",2),
        TransactionModel(10,90000.0,"Oct 27, 07:29 PM",2,6,"payment",2),
        TransactionModel(11,61000.0,"May 19, 05:47 PM",3,1,"topup",3),
        TransactionModel(12,16000.0,"Apr 01, 11:59 AM",3,2,"payment",3),
        TransactionModel(13,34000.0,"Oct 04, 01:31 AM",3,4,"payment",3),
        TransactionModel(14,150000.0,"Dec 28, 10:22 PM",3,5,"topup",3),
        TransactionModel(15,23000.0,"Mar 07, 12:39 AM",3,6,"topup",3),
        TransactionModel(16,18000.0,"Jan 13, 01:47 PM",4,1,"payment",4),
        TransactionModel(17,103500.0,"Jun 04, 04:18 AM",4,2,"topup",4),
        TransactionModel(18,102300.0,"Oct 17, 12:06 PM",4,3,"payment",4),
        TransactionModel(19,35900.0,"Jul 04, 10:20 AM",4,5,"topup",4),
        TransactionModel(20,56200.0,"Dec 09, 11:24 AM",4,6,"payment",4),
        TransactionModel(21,89300.0,"Feg 16, 07:24 PM",5,1,"topup",5),
        TransactionModel(22,169000.0,"Nov 23, 02:24 AM",5,2,"topup",5),
        TransactionModel(23,25000.0,"Apr 14, 12:24 AM",5,3,"payment",5),
        TransactionModel(24,92300.0,"Jun 30, 07:24 PM",5,4,"topup",5),
        TransactionModel(24,71000.0,"Aug 09, 04:24 PM",5,6,"payment",5),
        TransactionModel(24,52000.0,"Aug 22, 09:24 AM",6,1,"topup",6),
        TransactionModel(24,17800.0,"Nov 07, 01:24 PM",6,2,"topup",6),
        TransactionModel(24,47200.0,"Dec 01, 12:24 PM",6,3,"payment",6),
        TransactionModel(24,104900.0,"Jul 30, 09:24 AM",6,4,"topup",6),
        TransactionModel(24,75350.0,"Mar 19, 06:24 AM",6,5,"payment",6)
    )
  }
}