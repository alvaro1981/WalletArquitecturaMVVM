package com.example.walletarquitecturamvvm.presentation.ui.fragments

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.walletarquitecturamvvm.R
import com.example.walletarquitecturamvvm.data.local.ProviderUserModel
import com.example.walletarquitecturamvvm.data.model.TransactionModel
import com.example.walletarquitecturamvvm.data.model.UserModel
import com.example.walletarquitecturamvvm.databinding.ItemUserTransactionBinding
import java.text.DecimalFormat

class TransactionAdapter(val usuarioTransacciones:List<TransactionModel>):
    RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int): TransactionViewHolder {

         val bindingItem =
             ItemUserTransactionBinding.inflate(LayoutInflater.from(parent.context),parent,false)
         return TransactionViewHolder(bindingItem)
    }

    override fun getItemCount(): Int {
       return usuarioTransacciones.size
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.render(usuarioTransacciones[position])
    }

  inner class TransactionViewHolder(val bindingItem: ItemUserTransactionBinding)
      :RecyclerView.ViewHolder(bindingItem.root){

      val dec = DecimalFormat("+#,000;-#")
       @SuppressLint("ResourceAsColor", "SetTextI18n")
       fun render(transaction:TransactionModel){
           bindingItem.tvcontactname.text = ProviderUserModel.getUserForId(transaction.to_accountId)!!.name
           bindingItem.tvtransactiondate.text = transaction.transactionDate
          // bindingItem.tvtransactionmoney.text = transaction.amount.toString()
           bindingItem.tvtransactionmoney.text ="$ ${dec.format(transaction.amount)}"
           if (transaction.amount < 0) bindingItem.tvtransactionmoney.setTextColor(R.color.red)
           bindingItem.ivtransWalletContact.setImageResource(ProviderUserModel.
           getUserForId(transaction.to_accountId)!!.profilePicture)
       }
    }
}