package com.artufimtcev.sadapter.sample.adapteritem;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.artufimtcev.sadapter.AdapterItem;
import com.artufimtcev.sadapter.sample.R;


public class TextAdapterItem extends SelectableAdapterItem<TextAdapterItem.ViewHolder> {

	private String data;

	public TextAdapterItem(String data) {
		this.data = data;
	}


	@Override
	public void onBindViewHolder(ViewHolder holder) {
		holder.mTextView.setText(data);
	}


	@Override
	public ViewHolder createViewHolder(ViewGroup parent) {
		return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text, parent, false));
	}


	@Override
	public boolean hasSameDataAs(AdapterItem<? extends RecyclerView.ViewHolder> item) {
		if(this == item) return true;
		if(item == null || getClass() != item.getClass()) return false;

		TextAdapterItem that = (TextAdapterItem) item;

		return data != null ? data.equals(that.data) : that.data == null;
	}


	public static class ViewHolder extends RecyclerView.ViewHolder {

		private TextView mTextView;

		public ViewHolder(View itemView) {
			super(itemView);

			mTextView = (TextView) itemView.findViewById(R.id.text);
		}



	}
}
