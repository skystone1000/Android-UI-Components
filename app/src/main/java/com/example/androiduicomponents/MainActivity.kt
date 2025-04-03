package com.example.androiduicomponents

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androiduicomponents.atoms.AtomsActivity
import com.example.androiduicomponents.databinding.ActivityMainBinding
import com.example.androiduicomponents.molecules.MoleculesActivity
import com.example.androiduicomponents.organisms.OrganismsActivity
import com.example.androiduicomponents.others.OthersActivity
import com.example.androiduicomponents.templates.TemplatesActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inflate Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Components
        binding.btnAtoms.setOnClickListener{
            val intent = Intent(this, AtomsActivity::class.java)
            startActivity(intent)
        }
        binding.btnMolecules.setOnClickListener{
            val intent = Intent(this, MoleculesActivity::class.java)
            startActivity(intent)
        }
        binding.btnOrganism.setOnClickListener{
            val intent = Intent(this, OrganismsActivity::class.java)
            startActivity(intent)
        }
        binding.btnTemplates.setOnClickListener{
            val intent = Intent(this, TemplatesActivity::class.java)
            startActivity(intent)
        }
        binding.btnOthers.setOnClickListener{
            val intent = Intent(this, OthersActivity::class.java)
            startActivity(intent)
        }

    }
}