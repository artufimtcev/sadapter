package com.artufimtcev.sadapter.sample.shuffle;

import com.annimon.stream.Stream;
import com.annimon.stream.function.Function;
import com.artufimtcev.sadapter.SAdapter;
import com.artufimtcev.sadapter.sample.adapteritem.TextAdapterItem;

import java.util.List;


public class ShuffleAdapter extends SAdapter {

	public void setData(List<String> data) {
		clear();
		addAll(Stream.of(data).map(new Function<String, TextAdapterItem>() {
			@Override
			public TextAdapterItem apply(String s) {
				return new TextAdapterItem(s);
			}
		}).toList());
	}


	public void setDataUsingDiffUtil(List<String> data) {
		List<TextAdapterItem> textAdapterItems = Stream.of(data).map(new Function<String, TextAdapterItem>() {
			@Override
			public TextAdapterItem apply(String s) {
				return new TextAdapterItem(s);
			}
		}).toList();

		updateAll(textAdapterItems);
	}
}
