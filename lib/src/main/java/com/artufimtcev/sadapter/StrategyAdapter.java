package com.artufimtcev.sadapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public abstract class StrategyAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

	private final List<StrategyItem<? extends VH>> mItems = new ArrayList<>();
	private final List<Class<? extends StrategyItem>> mViewTypes = new ArrayList<>();

	// ----- Delegate adapter work to StrategyItems -----


	@Override
	public void onBindViewHolder(VH holder, int position) {
		// Delegate binding of the ViewHolder to the corresponding StrategyItem
		((StrategyItem<VH>) mItems.get(position)).onBindViewHolder(holder);
	}


	@Override
	public int getItemViewType(int position) {
		Class<? extends StrategyItem> viewTypeClass = mItems.get(position).getClass();
		if (!mViewTypes.contains(viewTypeClass)) {
			mViewTypes.add(viewTypeClass);
		}

		// Use index of StrategyItem's Class object in mViewTypes list as int that represents
		// this view type as int for RecyclerView.Adapter
		return mViewTypes.indexOf(viewTypeClass);
	}


	@Override
	public VH onCreateViewHolder(ViewGroup parent, int viewType) {
		// Use any of StrategyItems of this type to generate new ViewHolder
		return (VH) findItem(mViewTypes.get(viewType)).createViewHolder(parent);
	}


	@Override
	public int getItemCount() {
		return mItems.size();
	}


	// ----- Retrieving items -----


	public StrategyItem<? extends VH> get(int index) {
		return mItems.get(index);
	}


	public <T extends StrategyItem<? extends VH>> T findItem(Class<T> clazz) {
		for (StrategyItem<? extends VH> strategyItem : mItems) {
			if (strategyItem.getClass() == clazz) {
				//noinspection unchecked
				return (T) strategyItem;
			}
		}

		return null;
	}


	// ----- Adding items -----


	public void add(StrategyItem<? extends VH> item) {
		mItems.add(item);
		notifyItemInserted(mItems.size() - 1);
	}


	public void add(int index, StrategyItem<? extends VH> item) {
		mItems.add(index, item);
		notifyItemInserted(index);
	}


	public void addAll(Collection<? extends StrategyItem<? extends VH>> items) {
		int start = mItems.size();
		mItems.addAll(items);
		notifyItemRangeInserted(start, items.size());
	}


	// ----- Removing items -----


	public void remove(StrategyItem<? extends VH> item) {
		int index = indexOf(item);
		if (index < 0) return;
		mItems.remove(item);
		notifyItemRemoved(index);
	}


	public void remove(int index) {
		mItems.remove(index);
		notifyItemRemoved(index);
	}


	public void removeType(Class<? extends StrategyItem> type) {
		List<Range> removedItemsRange = performRemoveType(type);
		AdapterUtils.notifyItemRangeRemoved(this, removedItemsRange);
	}


	public void clear() {
		if (mItems.isEmpty()) {
			return;
		}

		int itemsCount = mItems.size();
		mItems.clear();
		notifyItemRangeRemoved(0, itemsCount);
	}


	// ----- Updating items -----


	public void set(int index, StrategyItem<? extends VH> item) {
		mItems.set(index, item);
		notifyItemChanged(index);
	}


	public void update(StrategyItem<? extends VH> item) {
		updateType(item, item.getClass());
	}


	public void updateType(StrategyItem<? extends VH> item, Class<? extends StrategyItem> type) {
		removeType(type);
		add(item);
	}


	public void updateType(List<? extends StrategyItem<? extends VH>> item, Class<? extends StrategyItem> type) {
		removeType(type);
		addAll(item);
	}


	// ----- Calculating indices -----


	public int indexOf(StrategyItem<? extends VH> item) {
		return mItems.indexOf(item);
	}


	public int indexOf(Class<? extends StrategyItem> type) {
		for (int i = 0; i < mItems.size(); i++) {
			if (mItems.get(i).getClass() == type) return i;
		}

		return -1;
	}


	// ----- Convenience -----


	public boolean isEmpty() {
		return mItems.isEmpty();
	}


	private List<Range> performRemoveType(Class<? extends StrategyItem> type) {
		List<Integer> removedItemIndicies = new ArrayList<>();

		for (int i = 0; i < mItems.size();) {
			if (mItems.get(i).getClass() == type) {
				mItems.remove(i);
				removedItemIndicies.add(i);
			} else {
				i++;
			}
		}

		return RangeUtils.groupIntsToRanges(removedItemIndicies);
	}
}