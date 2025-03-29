package com.example.androiduicomponents.molecules

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.androiduicomponents.R
import com.example.androiduicomponents.databinding.ActivityMainBinding
import com.example.androiduicomponents.databinding.ActivityMoleculesBinding
import com.example.androiduicomponents.molecules.fragments.View1ExpandableCardFragment
import com.example.androiduicomponents.molecules.fragments.View2ButtonToggleGroupListFragment

class MoleculesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMoleculesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_molecules)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inflate Binding
        binding = ActivityMoleculesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initial Views
        binding.btn1.setOnClickListener{
            openFragment(View1ExpandableCardFragment())
        }

        binding.btn2.setOnClickListener{
            openFragment(View2ButtonToggleGroupListFragment())
        }
    }

    private fun openFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()

    }
}