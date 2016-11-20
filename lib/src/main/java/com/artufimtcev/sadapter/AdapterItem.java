package com.artufimtcev.sadapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;


public abstract class AdapterItem<VH extends RecyclerView.ViewHolder> {

	public abstract void onBindViewHolder(VH holder);
	public abstract VH createViewHolder(ViewGroup parent);


	/**
	 * Define if this AdapterItem's appearance can be different while it displays the same data. This method must
	 * return true if you override {@link #hasSameAppearanceAs(AdapterItem) hasSameAppearanceAs} in this class.
	 * <P>
	 * You can improve SAdapter performance by returning false here if your AdapterItem doesn't have more than one
	 * appearance state (selected/progress/empty/...) and it's appearance depends on it's data only. By setting this
	 * to false, {@link #hasSameAppearanceAs(AdapterItem)} method will never be called.
	 *
	 * @return true if the item has various appearances, false otherwise
	 */
	public boolean hasDifferentAppearances() {
		return true;
	}


	/**
	 * Check equality of this AdapterItem's data to provided AdapterItem's data.
	 *
	 * @param item item to check data equality against
	 * @return true if this item's data is equal to the provided item's data, false otherwise
	 */
	public abstract boolean hasSameDataAs(AdapterItem<? extends RecyclerView.ViewHolder> item);


	/**
	 * Check equality of this AdapterItem's appearance to provided AdapterItem's appearance. This should check items'
	 * view state like selected state, progress state, etc.
	 *
	 * @param item item to check appearance equality against
	 * @return true if this item's appearance is equal to the provided item's appearance, false otherwise
	 */
	public boolean hasSameAppearanceAs(AdapterItem<? extends RecyclerView.ViewHolder> item) {
		// Default implementation assumes that item's appearance depends on the data only
		// and has only one state.
		return this.hasSameDataAs(item);
	}
}
