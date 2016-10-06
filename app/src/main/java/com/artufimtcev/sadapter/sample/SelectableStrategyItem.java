package com.artufimtcev.sadapter.sample;

import android.support.v7.widget.RecyclerView;

import com.artufimtcev.sadapter.StrategyItem;


public abstract class SelectableStrategyItem<VH extends RecyclerView.ViewHolder> implements StrategyItem<VH> {

	private boolean selected;


	public boolean isSelected() {
		return selected;
	}


	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
