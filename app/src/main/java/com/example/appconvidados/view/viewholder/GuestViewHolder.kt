package com.example.appconvidados.view.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.appconvidados.databinding.RowGuestBinding
import com.example.appconvidados.model.GuestModel

class GuestViewHolder(private  val bind: RowGuestBinding) : RecyclerView.ViewHolder(bind.root) {

    fun bind(guest: GuestModel){
    //utilizando viewBniding
       bind.textName.text = guest.name
    }
}