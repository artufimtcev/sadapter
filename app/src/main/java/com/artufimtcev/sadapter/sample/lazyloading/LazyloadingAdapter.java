package com.artufimtcev.sadapter.sample.lazyloading;

import com.artufimtcev.sadapter.SAdapter;
import com.artufimtcev.sadapter.sample.adapteritem.ProgressItem;


public class LazyloadingAdapter extends SAdapter {

	public void showProgress() {
		if(this.findFirst(ProgressItem.class) == null) {
			add(new ProgressItem());
		}
	}


	public void hideProgress() {
		this.removeType(ProgressItem.class);
	}
}
