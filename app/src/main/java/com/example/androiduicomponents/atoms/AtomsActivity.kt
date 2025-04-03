package com.example.androiduicomponents.atoms

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androiduicomponents.R
import com.example.androiduicomponents.databinding.ActivityAtomsBinding

class AtomsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAtomsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_atoms)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding = ActivityAtomsBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}