package com.example.effectivemobile.presentation.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.apimodels.Offer
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager

class OffersAdapter(private val offers: List<Offer>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val delegatesManager = AdapterDelegatesManager<List<Offer>>()

    init {
        delegatesManager.addDelegate(OffersAdapterDelegate())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegatesManager.onCreateViewHolder(parent, viewType)
    }

    override fun getItemCount(): Int = offers.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegatesManager.onBindViewHolder(offers, position, holder, null)
    }

}