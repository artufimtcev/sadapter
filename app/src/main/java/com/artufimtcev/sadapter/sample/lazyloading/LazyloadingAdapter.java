package com.artufimtcev.sadapter.sample.lazyloading;

import android.support.v7.widget.RecyclerView;

import com.artufimtcev.sadapter.SAdapter;
import com.artufimtcev.sadapter.sample.ProgressItem;


public class LazyloadingAdapter extends SAdapter<RecyclerView.ViewHolder> {

	public void showProgress() {
		if(this.findFirst(ProgressItem.class) == null) {
			add(new ProgressItem());
		}
	}


	public void hideProgress() {
		this.removeType(ProgressItem.class);
	}
}
