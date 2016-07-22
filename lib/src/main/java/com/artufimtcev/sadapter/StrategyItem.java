package com.artufimtcev.sadapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;


public interface StrategyItem<VH extends RecyclerView.ViewHolder> {

	void onBindViewHolder(VH holder);
	VH createViewHolder(ViewGroup parent);
}
