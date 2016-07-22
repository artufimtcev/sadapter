package com.artufimtcev.sadapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public abstract class StrategyAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

	private final List<StrategyItem<? extends VH>> mItems = new ArrayList<>();
	private final List<Class<? extends StrategyItem<? extends VH>>> mViewTypes = new ArrayList<>();


	@Override
	public void onBindViewHolder(VH holder, int position) {
		((StrategyItem<VH>) mItems.get(position)).onBindViewHolder(holder);
	}


	@Override
	public int getItemViewType(int position) {
		int viewType = mViewTypes.indexOf(mItems.get(position).getClass());
		if (viewType != -1) {
			return viewType;
		} else {
			// TODO: Errors
			throw new RuntimeException();
		}
	}


	@Override
	public VH onCreateViewHolder(ViewGroup parent, int viewType) {
		return findItem(mViewTypes.get(viewType)).createViewHolder(parent);
	}


	public StrategyItem<? extends VH> get(int index) {
		return mItems.get(index);
	}


	public StrategyItem<? extends VH> findItem(Class<? extends StrategyItem<? extends VH>> clazz) {
		for (StrategyItem<? extends VH> strategyItem : mItems) {
			if (strategyItem.getClass() == clazz) return strategyItem;
		}

		return null;
	}


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


	public void removeType(Class<? extends StrategyItem> type) {
		for (int i = 0; i < mItems.size();) {
			if (mItems.get(i).getClass() == type) {
				mItems.remove(i);
				notifyItemChanged(i);
			} else {
				i++;
			}
		}
	}


	public int indexOf(StrategyItem<? extends VH> item) {
		return mItems.indexOf(item);
	}


	public int indexOf(Class<StrategyItem> type) {
		for (int i = 0; i < mItems.size(); i++) {
			if (mItems.get(i).getClass() == type) return i;
		}

		return -1;
	}


	public boolean isEmpty() {
		return mItems.isEmpty();
	}


	public void clear() {
		mItems.clear();
	}
}
