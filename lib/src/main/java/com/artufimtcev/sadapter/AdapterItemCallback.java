package com.artufimtcev.sadapter;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class AdapterItemCallback<VH extends RecyclerView.ViewHolder> extends DiffUtil.Callback {

	private List<AdapterItem<? extends VH>> listBeforeChange;
	private List<AdapterItem<? extends VH>> listAfterChange;

	public AdapterItemCallback(List<AdapterItem<? extends VH>> listBeforeChange,
							   List<AdapterItem<? extends VH>> listAfterChange) {
		this.listBeforeChange = listBeforeChange;
		this.listAfterChange = listAfterChange;
	}

	@Override
	public int getOldListSize() {
		return listBeforeChange.size();
	}


	@Override
	public int getNewListSize() {
		return listAfterChange.size();
	}


	@Override
	public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
		return listBeforeChange.get(oldItemPosition).isSimilarTo(listAfterChange.get(newItemPosition));
	}


	@Override
	public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
		return listBeforeChange.get(oldItemPosition).equals(listAfterChange.get(newItemPosition));
	}
}
