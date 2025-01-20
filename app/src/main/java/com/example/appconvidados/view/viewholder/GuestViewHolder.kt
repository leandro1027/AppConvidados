package com.example.appconvidados.view.viewholder

import android.content.DialogInterface
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.appconvidados.databinding.RowGuestBinding
import com.example.appconvidados.model.GuestModel
import com.example.appconvidados.view.listener.OnGuestListener

class GuestViewHolder(private  val bind: RowGuestBinding, private val listenner: OnGuestListener) : RecyclerView.ViewHolder(bind.root) {

    fun bind(guest: GuestModel){
    //utilizando viewBniding
       bind.textName.text = guest.name

        bind.textName.setOnClickListener{
            listenner.onClick(guest.id)
        }

        bind.textName.setOnClickListener{
            AlertDialog.Builder(itemView.context)
                .setTitle("Remoção do convidado")
                .setMessage("Deseja remover?")
                .setPositiveButton("Sim"
                ) { dialog, which ->
                    listenner.onDelete(guest.id)
                }
                .setNegativeButton("Não", null)
                .create()
                .show()
            true
        }
    }
}