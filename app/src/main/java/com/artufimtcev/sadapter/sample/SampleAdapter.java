package com.artufimtcev.sadapter.sample;

import android.support.v7.widget.RecyclerView;

import com.artufimtcev.sadapter.StrategyAdapter;
import com.artufimtcev.sadapter.StrategyItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class SampleAdapter extends StrategyAdapter<RecyclerView.ViewHolder> {

	public SampleAdapter() {
		init();
	}


	public void updateTexts() {
		List<TextStrategyItem> strategyItems = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			strategyItems.add(new TextStrategyItem("New Huehue"));
		}

		updateType(strategyItems, TextStrategyItem.class);
	}


	private void init() {
		int count = 30;
		int textItemsCount = (int) (Math.random() * count);

		List<StrategyItem<? extends RecyclerView.ViewHolder>> strategyItems = new ArrayList<>();

		for (int i = 0; i < count; i++) {
			if (i < textItemsCount) {
				strategyItems.add(new TextStrategyItem("Huehue"));
			} else {
				strategyItems.add(new ButtonStrategyItem("Button"));
			}
		}

		Collections.shuffle(strategyItems);

		addAll(strategyItems);
	}
}
