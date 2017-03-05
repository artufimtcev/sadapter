package com.artufimtcev.sadapter.sample;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.artufimtcev.sadapter.AdapterItem;
import com.artufimtcev.sadapter.SAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SampleAdapter extends SAdapter<RecyclerView.ViewHolder> {

	public SampleAdapter() {
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
