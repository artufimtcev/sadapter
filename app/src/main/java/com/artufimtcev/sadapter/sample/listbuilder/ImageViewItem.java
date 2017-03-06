package com.artufimtcev.sadapter.sample.listbuilder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.artufimtcev.sadapter.AdapterItem;
import com.artufimtcev.sadapter.sample.R;


public class ImageViewItem extends AdapterItem<ImageViewItem.ViewHolder> {

	@Override
	public void onBindViewHolder(ViewHolder holder) {

	}


	@Override
	public ViewHolder createViewHolder(ViewGroup parent) {
		return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.imageview_item, parent, false));
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
