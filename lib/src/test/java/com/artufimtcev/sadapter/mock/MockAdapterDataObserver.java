package com.artufimtcev.sadapter.mock;

import android.support.v7.widget.RecyclerView;


public class MockAdapterDataObserver extends RecyclerView.AdapterDataObserver {



	@Override
	public void onChanged() {
		super.onChanged();
	}


	@Override
	public void onItemRangeChanged(int positionStart, int itemCount) {
		super.onItemRangeChanged(positionStart, itemCount);
	}


	@Override
	public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
		super.onItemRangeChanged(positionStart, itemCount, payload);
	}


	@Override
	public void onItemRangeInserted(int positionStart, int itemCount) {
		super.onItemRangeInserted(positionStart, itemCount);
	}


	@Override
	public void onItemRangeRemoved(int positionStart, int itemCount) {
		super.onItemRangeRemoved(positionStart, itemCount);
	}


	@Override
	public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
		super.onItemRangeMoved(fromPosition, toPosition, itemCount);
	}
}
