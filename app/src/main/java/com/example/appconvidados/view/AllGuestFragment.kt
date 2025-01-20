package com.example.appconvidados.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.DEFAULT_ARGS_KEY
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appconvidados.constants.DataBaseConstants
import com.example.appconvidados.databinding.FragmentAllGuestsBinding
import com.example.appconvidados.view.adapter.GuestAdapter
import com.example.appconvidados.view.listener.OnGuestListener
import com.example.appconvidados.viewmodel.AllGuestViewModel


class AllGuestFragment : Fragment() {

    private var _binding: FragmentAllGuestsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : AllGuestViewModel
    private val adapter = GuestAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(AllGuestViewModel::class.java)
        _binding = FragmentAllGuestsBinding.inflate(inflater, container, false)

        //layout
        binding.recyclerAllGuests.layoutManager = LinearLayoutManager(context)

        //adapter
        binding.recyclerAllGuests.adapter = adapter

        val listener = object : OnGuestListener {

            override fun onClick(id : Int) {
                val intent = Intent(context, GuestFormActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(DataBaseConstants.GUEST.ID, id)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id : Int) {
                viewModel.delete(id)
                viewModel.getAll()
            }

        }

        adapter.attachListenner(listener)

        viewModel.getAll()

        observe()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observe(){
        viewModel.guests.observe(viewLifecycleOwner){
            adapter.updateGuests(it)

        }
    }
}