package com.artufimtcev.sadapter.sample.listbuilder;

import android.support.v7.widget.RecyclerView;

import com.artufimtcev.sadapter.AdapterItem;


public abstract class SelectableAdapterItem<VH extends RecyclerView.ViewHolder> extends AdapterItem<VH> {

	private boolean selected;


	public boolean isSelected() {
		return selected;
	}


	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
