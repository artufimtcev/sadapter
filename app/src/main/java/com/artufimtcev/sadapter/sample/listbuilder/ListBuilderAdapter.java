package com.artufimtcev.sadapter.sample.listbuilder;

import android.support.v7.widget.RecyclerView;

import com.artufimtcev.sadapter.SAdapter;


public class ListBuilderAdapter extends SAdapter<RecyclerView.ViewHolder> {

	public ListBuilderAdapter() {
	}

	public void addText(String text) {
		this.add(new TextAdapterItem(text));
	}


	public void addButton(String text) {
		this.add(new ButtonAdapterItem(text));
	}


	public void addImage() {
		this.add(new ImageViewItem());
	}
}
