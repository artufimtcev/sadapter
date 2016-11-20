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
		init();
	}


	public void updateTexts() {
		List<TextAdapterItem> strategyItems = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			strategyItems.add(new TextAdapterItem("New Huehue"));
		}

		updateType(strategyItems, TextAdapterItem.class);
	}


	private void init() {
		int count = 30;
		int textItemsCount = (int) (Math.random() * count);

		List<AdapterItem<? extends RecyclerView.ViewHolder>> strategyItems = new ArrayList<>();

		for (int i = 0; i < count; i++) {
			if (i < textItemsCount) {
				strategyItems.add(new TextAdapterItem("Huehue"));
			} else {
				strategyItems.add(new ButtonAdapterItem("Button"));
			}
		}

		Log.d("TEST", "Adding " + (count - textItemsCount) + " buttons and " + textItemsCount + " textviews");

		Collections.shuffle(strategyItems);

		addAll(strategyItems);
	}
}
