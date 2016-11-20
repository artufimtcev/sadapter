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
		return listBeforeChange.get(oldItemPosition).hasSameDataAs(listAfterChange.get(newItemPosition));
	}


	@Override
	public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
		// This method is called only when areItemsTheSame(int, int) returns true for the same indices, which means
		// those items has the same data. For items that have their appearance dependent on it's data only, we leave
		// a possibility for small optimization by not calling equality check method again.
		return !listBeforeChange.get(oldItemPosition).hasDifferentAppearances() ||
				listBeforeChange.get(oldItemPosition).hasSameAppearanceAs(listAfterChange.get(newItemPosition));
	}
}
