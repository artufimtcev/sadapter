package com.artufimtcev.sadapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;


public abstract class AdapterItem<VH extends RecyclerView.ViewHolder> {

	public abstract void onBindViewHolder(VH holder);
	public abstract VH createViewHolder(ViewGroup parent);


	@Override
	public abstract boolean equals(Object o);
	@Override
	public abstract int hashCode();


	public boolean isSimilarTo(AdapterItem<? extends RecyclerView.ViewHolder> item) {
		return this.equals(item);
	}
}
