package com.example.appconvidados.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appconvidados.constants.DataBaseConstants
import com.example.appconvidados.databinding.FragmentPresentBinding
import com.example.appconvidados.view.adapter.GuestAdapter
import com.example.appconvidados.view.listener.OnGuestListener
import com.example.appconvidados.viewmodel.GuestsViewModel

class PresentFragment : Fragment() {

    private var _binding: FragmentPresentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: GuestsViewModel
    private val adapter = GuestAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewModel = ViewModelProvider(this).get(GuestsViewModel::class.java)

        _binding = FragmentPresentBinding.inflate(inflater, container, false)
        //layout
        binding.recyclerGuests.layoutManager = LinearLayoutManager(context)

        //adapter
        binding.recyclerGuests.adapter = adapter

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
                viewModel.getPresent()
            }

        }

        adapter.attachListenner(listener)
        observe()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getPresent()
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