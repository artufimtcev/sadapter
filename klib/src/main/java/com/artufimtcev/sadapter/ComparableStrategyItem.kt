package com.artufimtcev.sadapter

import android.support.v7.widget.RecyclerView

interface ComparableStrategyItem<VH : RecyclerView.ViewHolder> : StrategyItem<VH>, Comparable<ComparableStrategyItem<VH>> {

    val sectionOrderNumber: Int

    override fun compareTo(other: ComparableStrategyItem<VH>): Int {
        val orderNumberComparisonResult = sectionOrderNumber.compareTo(other.sectionOrderNumber)
        return if (orderNumberComparisonResult == 0) {
            compareWithinSection(other)
        } else orderNumberComparisonResult
    }


    fun compareWithinSection(other: ComparableStrategyItem<VH>): Int
}