package com.artufimtcev.sadapter.sample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.artufimtcev.sadapter.AdapterItem;


public class ProgressItem extends AdapterItem<ProgressItem.ViewHolder> {


	@Override
	public void onBindViewHolder(ViewHolder holder) {

	}


	@Override
	public ViewHolder createViewHolder(ViewGroup parent) {
		return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_progress, parent, false));
	}


	@Override
	public boolean hasSameDataAs(AdapterItem<? extends RecyclerView.ViewHolder> item) {
		return false;
	}


	static class ViewHolder extends RecyclerView.ViewHolder {

		public ViewHolder(View itemView) {
			super(itemView);
		}
	}
}
