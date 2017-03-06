package com.artufimtcev.sadapter.sample.listbuilder;

import com.artufimtcev.sadapter.SAdapter;
import com.artufimtcev.sadapter.sample.adapteritem.ButtonAdapterItem;
import com.artufimtcev.sadapter.sample.adapteritem.TextAdapterItem;


public class ListBuilderAdapter extends SAdapter {

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
