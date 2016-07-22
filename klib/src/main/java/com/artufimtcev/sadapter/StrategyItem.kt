package com.artufimtcev.sadapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

interface StrategyItem<VH : RecyclerView.ViewHolder> {

    fun onBindViewHolder(holder: VH)
    fun createViewHolder(parent: ViewGroup): VH
}
