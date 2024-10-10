package com.example.effectivemobile.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.effectivemobile.EffectiveMobileApp
import com.example.effectivemobile.R
import com.example.effectivemobile.databinding.FragmentFavouritesBinding
import com.example.effectivemobile.presentation.adapters.vacancies.VacanciesAdapter
import com.example.effectivemobile.presentation.factory.MainViewModelFactory
import com.example.effectivemobile.presentation.viewmodel.MainViewModel
import javax.inject.Inject


class FavouritesFragment : Fragment() {

    private lateinit var binding: FragmentFavouritesBinding

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory
    private lateinit var mainViewModel: MainViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavouritesBinding.inflate(inflater)
        (requireActivity().application as EffectiveMobileApp).getAppComponent().inject(this)
        mainViewModel =
            ViewModelProvider(requireActivity(), mainViewModelFactory)[MainViewModel::class.java]
        mainViewModel.getAllFavourites()
        mainViewModel.getFavouritesIds()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.favouritesRV.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        binding.favouritesRV.visibility = View.GONE
        binding.favouriteCountTitle.visibility = View.GONE
        binding.favouriteProgressBar.visibility = View.VISIBLE


        mainViewModel.favouritesLiveData.observe(viewLifecycleOwner) { allFavourites ->

            binding.favouriteCountTitle.text =
                mainViewModel.getVacancyDeclension(allFavourites.size)
            var vacancyList: Int = allFavourites.toMutableList().size

            mainViewModel.favouritesIdsLiveData.observe(viewLifecycleOwner) { allFavouritesIds ->

                binding.favouritesRV.visibility = View.VISIBLE
                binding.favouriteCountTitle.visibility = View.VISIBLE
                binding.favouriteProgressBar.visibility = View.GONE

                val adapter = VacanciesAdapter(allFavourites.toMutableList(),
                    allFavouritesIds.toMutableList(),
                    { monthNumber ->
                        mainViewModel.getMonthName(monthNumber)
                    },
                    { peopleCount ->
                        mainViewModel.getPeopleDeclension(peopleCount)
                    },
                    true,
                    { clickedVacancy ->
                        vacancyList -= 1
                        binding.favouriteCountTitle.text =
                            mainViewModel.getVacancyDeclension(vacancyList)
                        mainViewModel.onClickVacancyInFavouritesFragment(clickedVacancy)
                    }, {
                        requireActivity().supportFragmentManager.beginTransaction()
                            .addToBackStack(null)
                            .replace(R.id.mainFragmentHolder, VacancyFragment())
                            .commit()

                    })
                binding.favouritesRV.adapter = adapter


            }

        }
    }


}