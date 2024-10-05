package com.example.effectivemobile.presentation.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.effectivemobile.EffectiveMobileApp
import com.example.effectivemobile.databinding.ActivityMainBinding
import com.example.effectivemobile.presentation.factory.MainViewModelFactory
import com.example.effectivemobile.presentation.viewmodel.MainViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        (application as EffectiveMobileApp).getAppComponent().inject(this)
        mainViewModel = ViewModelProvider(this, mainViewModelFactory)[MainViewModel::class.java]

        

    }
}