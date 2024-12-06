package com.example.appconvidados.view

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.appconvidados.viewmodel.GuestFormViewModel
import com.example.appconvidados.R
import com.example.appconvidados.databinding.ActivityGuestFormBinding
import com.example.appconvidados.model.GuestModel

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var viewModel: GuestFormViewModel

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

        viewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)
        binding.buttonEnviar.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_enviar){
            val name = binding.editTextName.text.toString()
            val presence = binding.radioPresent.isChecked

            val guest = GuestModel(0, name, presence)
            viewModel.insert(guest)

        }
    }
}