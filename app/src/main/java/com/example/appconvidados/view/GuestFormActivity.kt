package com.example.appconvidados.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.appconvidados.viewmodel.GuestFormViewModel
import com.example.appconvidados.R
import com.example.appconvidados.constants.DataBaseConstants
import com.example.appconvidados.databinding.ActivityGuestFormBinding
import com.example.appconvidados.model.GuestModel
import java.util.Observer

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var viewModel: GuestFormViewModel

    private  var guestId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel = ViewModelProvider(this)[GuestFormViewModel::class.java]

        binding.buttonEnviar.setOnClickListener(this)
        binding.radioPresent.isChecked = true

        loadData()
    }

    override fun onClick(view: View) {
        if(view.id == R.id.button_enviar){
            val name = binding.editTextName.text.toString()
            val presence = binding.radioPresent.isChecked

            val model = GuestModel(guestId, name, presence)
            viewModel.save(model)
            finish()

        }
    }
    
    private fun observe(){
        viewModel.guests.observe(this, androidx.lifecycle.Observer {
            binding.editTextName.setText(it.name)
            if (it.presence) {
                binding.radioPresent.isChecked = true
            } else {
                binding.radioAbsent.isChecked = true
            }
        })

        viewModel.saveGuest.observe(this, androidx.lifecycle.Observer {
            if (it.sucess) {
                Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
                finish()
            }
        })
}

    private fun loadData(){
        val bundle = intent.extras
        if (bundle != null){
            guestId = bundle.getInt(DataBaseConstants.GUEST.ID)
            viewModel.get(guestId)
        }
    }
}