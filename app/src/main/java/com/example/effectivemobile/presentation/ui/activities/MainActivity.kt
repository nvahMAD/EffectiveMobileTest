package com.example.effectivemobile.presentation.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.effectivemobile.EffectiveMobileApp
import com.example.effectivemobile.R
import com.example.effectivemobile.databinding.ActivityMainBinding
import com.example.effectivemobile.presentation.adapters.VacanciesAdapter
import com.example.effectivemobile.presentation.factory.MainViewModelFactory
import com.example.effectivemobile.presentation.ui.fragments.FavouritesFragment
import com.example.effectivemobile.presentation.ui.fragments.SearchFragment
import com.example.effectivemobile.presentation.viewmodel.MainViewModel
import com.google.android.material.badge.BadgeDrawable
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
        (application as EffectiveMobileApp).getAppComponent().inject(this)
        mainViewModel = ViewModelProvider(this, mainViewModelFactory)[MainViewModel::class.java]
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.mainFragmentHolder, SearchFragment())
            .commit()

        binding.bottomNav.setOnNavigationItemSelectedListener {item ->
            when(item.itemId){
                R.id.navSearch ->{
                    supportFragmentManager.beginTransaction()
                        .remove(FavouritesFragment())
                        .replace(R.id.mainFragmentHolder, SearchFragment())
                        .commit()
                    true
                }
                R.id.navFavourites ->{
                    supportFragmentManager.beginTransaction()
                        .remove(SearchFragment())
                        .replace(R.id.mainFragmentHolder, FavouritesFragment())
                        .commit()
                    true
                }
                else -> {
                    true
                }
            }
        }

        mainViewModel.favouriteCountLiveData.observe(this) { count ->
            val badge : BadgeDrawable = binding.bottomNav.getOrCreateBadge(R.id.navFavourites)
            if (count == 0){
                badge.isVisible = false
            }
            else {
                badge.number = count
                badge.isVisible = true
            }
        }

    }
}