package com.artufimtcev.sadapter;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


public class SAdapter extends RecyclerView.Adapter implements Iterable<AdapterItem<? extends ViewHolder>> {

	private final List<AdapterItem<? extends ViewHolder>> mItems = new ArrayList<>();
	private final List<Class<? extends AdapterItem>> mViewTypes = new ArrayList<>();

	// ----- Delegate adapter work to StrategyItems -----


	@Override
	public int getItemViewType(int position) {
		Class<? extends AdapterItem> viewTypeClass = mItems.get(position).getClass();
		if(!mViewTypes.contains(viewTypeClass)) {
			mViewTypes.add(viewTypeClass);
		}

		// Use index of AdapterItem's Class object in mViewTypes list as int that represents
		// this view type as int for RecyclerView.Adapter
		return mViewTypes.indexOf(viewTypeClass);
	}


	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		// Use any of StrategyItems of this type to generate new ViewHolder
		return findFirst(mViewTypes.get(viewType)).createViewHolder(parent);
	}


	@Override
	public int getItemCount() {
		return mItems.size();
	}


	/**
	 * Returns an iterator of all items in this adapter.
	 * <p>
	 * NOTE: Method returns a list iterator wrapped into a {@link ReadIterator}, meaning that calling remove() method
	 * on the iterator will throw an {@link UnsupportedOperationException}.
	 *
	 * @return ReadIterator for this adapter.
	 */
	@Override
	public Iterator<AdapterItem<? extends ViewHolder>> iterator() {
		return new ReadIterator<>(mItems.iterator());
	}


	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		// Delegate binding of the ViewHolder to the corresponding AdapterItem
		((AdapterItem<? super ViewHolder>) mItems.get(position)).onBindViewHolder(holder);
	}


	// ----- Retrieving items -----


	public AdapterItem<? extends ViewHolder> get(int index) {
		return mItems.get(index);
	}


	public <T extends AdapterItem<? extends ViewHolder>> T findFirst(Class<T> clazz) {
		for(AdapterItem<? extends ViewHolder> strategyItem : mItems) {
			if(strategyItem.getClass() == clazz) {
				//noinspection unchecked
				return (T) strategyItem;
			}
		}

		return null;
	}


	public <T extends AdapterItem<? extends ViewHolder>> List<T> findAll(Class<T> clazz) {
		List<T> result = new ArrayList<>();

		for(AdapterItem<? extends ViewHolder> strategyItem : mItems) {
			if(strategyItem.getClass() == clazz) {
				//noinspection unchecked
				result.add((T) strategyItem);
			}
		}

		return result;
	}


	// ----- Adding items -----


	public void add(AdapterItem<? extends ViewHolder> item) {
		mItems.add(item);
		notifyItemInserted(mItems.size() - 1);
	}


	public void add(int index, AdapterItem<? extends ViewHolder> item) {
		mItems.add(index, item);
		notifyItemInserted(index);
	}


	public void addAll(Collection<? extends AdapterItem<? extends ViewHolder>> items) {
		int start = mItems.size();
		mItems.addAll(items);
		notifyItemRangeInserted(start, items.size());
	}


	// ----- Removing items -----


	public void remove(AdapterItem<? extends ViewHolder> item) {
		int index = indexOf(item);
		if(index < 0) return;
		mItems.remove(item);
		notifyItemRemoved(index);
	}


	public void remove(int index) {
		mItems.remove(index);
		notifyItemRemoved(index);
	}


	public void removeType(Class<? extends AdapterItem> type) {
		List<AdapterItem<? extends ViewHolder>> itemsListSnapshot = this.getItemsSnapshot();
		this.performRemoveType(type);
		DiffUtil.calculateDiff(new AdapterItemCallback<>(itemsListSnapshot, mItems), false).dispatchUpdatesTo(this);
	}


	public void clear() {
		if(mItems.isEmpty()) {
			return;
		}

		int itemsCount = mItems.size();
		mItems.clear();
		notifyItemRangeRemoved(0, itemsCount);
	}


	// ----- Updating items -----


	public void set(int index, AdapterItem<? extends ViewHolder> item) {
		mItems.set(index, item);
		notifyItemChanged(index);
	}


	public void update(AdapterItem<? extends ViewHolder> item) {
		updateType(item, item.getClass());
	}


	public void updateType(AdapterItem<? extends ViewHolder> item, Class<? extends AdapterItem> type) {
		List<AdapterItem<? extends ViewHolder>> itemsListSnapshot = this.getItemsSnapshot();

		this.performRemoveType(type);
		this.performAdd(item);

		long start = System.currentTimeMillis();
		DiffUtil.calculateDiff(new AdapterItemCallback<>(itemsListSnapshot, mItems), false).dispatchUpdatesTo(this);
		Log.d("TEST", "Processing adapter items took " + (System.currentTimeMillis() - start) + " millis");
	}


	public void updateType(List<? extends AdapterItem<? extends ViewHolder>> items, Class<? extends AdapterItem> type) {
		List<AdapterItem<? extends ViewHolder>> itemsListSnapshot = this.getItemsSnapshot();

		this.performRemoveType(type);
		this.performAdd(items);

		long start = System.currentTimeMillis();
		DiffUtil.calculateDiff(new AdapterItemCallback<>(itemsListSnapshot, mItems), false).dispatchUpdatesTo(this);
		Log.d("TEST", "Processing adapter items took " + (System.currentTimeMillis() - start) + " millis");
	}


	public void updateAll(List<? extends AdapterItem<? extends ViewHolder>> items) {
		// Create items snapshot
		List<AdapterItem<? extends ViewHolder>> oldListCopy = getItemsSnapshot();

		mItems.clear();
		mItems.addAll(items);

		DiffUtil.calculateDiff(new AdapterItemCallback<>(oldListCopy, mItems)).dispatchUpdatesTo(this);
	}


	// ----- Replace items -----


	public void replaceAll(List<? extends AdapterItem<? extends ViewHolder>> items) {
		mItems.clear();
		mItems.addAll(items);
		notifyDataSetChanged();
	}


	// ----- Calculating indices -----


	public int indexOf(AdapterItem<? extends ViewHolder> item) {
		return mItems.indexOf(item);
	}


	public int indexOf(Class<? extends AdapterItem> type) {
		for(int i = 0; i < mItems.size(); i++) {
			if(mItems.get(i).getClass() == type) return i;
		}

		return -1;
	}


	// ----- Convenience -----


	public int getItemCountForType(Class<? extends AdapterItem> type) {
		int count = 0;
		for(int i = 0; i < getItemCount(); i++) {
			if(get(i).getClass() == type) count++;
		}
		return count;
	}


	public <T extends AdapterItem<? extends ViewHolder>> List<T> getAllItemsForType(Class<T> type) {
		List<T> result = new ArrayList<>();
		for(int i = 0; i < getItemCount(); i++) {
			if(get(i).getClass() == type) {
				//noinspection unchecked
				result.add((T) get(i));
			}
		}
		return result;
	}


	public boolean isEmpty() {
		return mItems.isEmpty();
	}


	private void performRemoveType(Class<? extends AdapterItem> type) {
		for(int i = 0; i < mItems.size(); ) {
			if(mItems.get(i).getClass() == type) {
				mItems.remove(i);
			} else {
				i++;
			}
		}
	}


	private void performAdd(AdapterItem<? extends ViewHolder> item) {
		mItems.add(item);
	}


	private void performAdd(List<? extends AdapterItem<? extends ViewHolder>> items) {
		mItems.addAll(items);
	}


	private List<AdapterItem<? extends ViewHolder>> getItemsSnapshot() {
		List<AdapterItem<? extends ViewHolder>> snapshot = new ArrayList<>();
		snapshot.addAll(mItems);
		return snapshot;
	}
}