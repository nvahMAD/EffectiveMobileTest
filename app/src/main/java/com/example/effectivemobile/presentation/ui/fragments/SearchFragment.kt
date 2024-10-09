package com.example.effectivemobile.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.model.apimodels.Vacancy
import com.example.effectivemobile.EffectiveMobileApp
import com.example.effectivemobile.R
import com.example.effectivemobile.databinding.FragmentSearchBinding
import com.example.effectivemobile.presentation.adapters.OffersAdapter
import com.example.effectivemobile.presentation.adapters.VacanciesAdapter
import com.example.effectivemobile.presentation.factory.MainViewModelFactory
import com.example.effectivemobile.presentation.viewmodel.MainViewModel
import javax.inject.Inject


class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory
    private lateinit var mainViewModel: MainViewModel

    private var isMoreClicked = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater)
        (requireActivity().application as EffectiveMobileApp).getAppComponent().inject(this)
        mainViewModel =
            ViewModelProvider(requireActivity(), mainViewModelFactory)[MainViewModel::class.java]
        mainViewModel.getFavouritesIds()
        mainViewModel.getAllFavourites()

        binding.vacancyRV.visibility = View.GONE
        binding.moreVacanciesButton.visibility = View.GONE
        binding.upperBlock.visibility = View.GONE
        binding.searchProgressBar.visibility = View.VISIBLE

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (isMoreClicked) {
                        binding.lessBlock.visibility = View.VISIBLE
                        binding.moreBlock.visibility = View.GONE
                        binding.moreVacanciesButton.visibility = View.VISIBLE
                        isMoreClicked = false
                        mainViewModel.getFavouritesIds()
                        getAllVacancies(true)
                    } else {
                        isEnabled = false
                        requireActivity().onBackPressed()
                    }
                }

            })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vacancyRV.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        binding.offersRV.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        getAllVacancies(true)

        binding.searchButton.setOnClickListener {

            if (isMoreClicked) {
                binding.searchButton.setImageResource(R.drawable.search_button)
                requireActivity().onBackPressed()
            }

        }

        binding.moreVacanciesButton.setOnClickListener {
            binding.searchButton.setImageResource(R.drawable.back_icon)
            isMoreClicked = true
            binding.lessBlock.visibility = View.GONE
            binding.moreBlock.visibility = View.VISIBLE
            binding.moreVacanciesButton.visibility = View.GONE
            mainViewModel.getFavouritesIds()
            getAllVacancies(false)
        }

    }

    private fun getAllVacancies(shortList: Boolean) {
        mainViewModel.apiResponseData.observe(viewLifecycleOwner) { allVacancies ->

            if (allVacancies.offers != null) {
                binding.offersRV.adapter = OffersAdapter(allVacancies.offers)
            } else {
                binding.offersRV.visibility = View.GONE
            }
            binding.allVacanciesCountTextView.text =
                mainViewModel.getVacancyDeclension(allVacancies.vacancies.size)
            binding.moreVacanciesButtonTextView.text =
                "Еще ${mainViewModel.getVacancyDeclension(allVacancies.vacancies.size - 3)}"

            val vacancyList: MutableList<Vacancy> = if (shortList) {
                allVacancies.vacancies.take(3).toMutableList()
            } else {
                allVacancies.vacancies.toMutableList()
            }
            mainViewModel.favouritesIdsLiveData.observe(viewLifecycleOwner) { favIds ->

                binding.vacancyRV.visibility = View.VISIBLE
                binding.upperBlock.visibility = View.VISIBLE
                binding.searchProgressBar.visibility = View.GONE
                if (shortList) {
                    binding.moreVacanciesButton.visibility = View.VISIBLE
                } else {

                    binding.moreVacanciesButton.visibility = View.GONE
                }

                val adapter = VacanciesAdapter(
                    vacancyList,
                    favIds.toMutableList(),
                    { monthNumber -> mainViewModel.getMonthName(monthNumber) },
                    { peopleCount ->
                        mainViewModel.getPeopleDeclension(peopleCount)
                    },
                    false,
                    { clickedVacancy ->
                        mainViewModel.onClickVacancyInSearchFragment(clickedVacancy)
                    }, {
                        requireActivity().supportFragmentManager.beginTransaction()
                            .addToBackStack(null)
                            .replace(R.id.mainFragmentHolder, VacancyFragment())
                            .commit()

                    })

                binding.vacancyRV.adapter = adapter
            }
        }
    }


}