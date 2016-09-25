package com.artufimtcev.sadapter;

import android.support.v7.widget.RecyclerView;

import java.util.List;


final class AdapterUtils {

	private AdapterUtils() {}


	static void notifyItemRangeInserted(RecyclerView.Adapter adapter, Range range) {
		adapter.notifyItemRangeInserted(range.from, range.to);
	}


	static void notifyItemRangeChanged(RecyclerView.Adapter adapter, Range range) {
		adapter.notifyItemRangeChanged(range.from, range.to);
	}


	static void notifyItemRangeRemoved(RecyclerView.Adapter adapter, Range range) {
		adapter.notifyItemRangeRemoved(range.from, range.to);
	}


	static void notifyItemRangeInserted(RecyclerView.Adapter adapter, List<Range> rangesList) {
		for (Range range : rangesList) {
			notifyItemRangeInserted(adapter, range);
		}
	}


	static void notifyItemRangeChanged(RecyclerView.Adapter adapter, List<Range> rangesList) {
		for (Range range : rangesList) {
			notifyItemRangeChanged(adapter, range);
		}
	}


	static void notifyItemRangeRemoved(RecyclerView.Adapter adapter, List<Range> rangesList) {
		for (Range range : rangesList) {
			notifyItemRangeRemoved(adapter, range);
		}
	}
}
