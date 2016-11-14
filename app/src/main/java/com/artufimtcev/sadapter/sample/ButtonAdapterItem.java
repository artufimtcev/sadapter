package com.artufimtcev.sadapter.sample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class ButtonAdapterItem extends SelectableAdapterItem<ButtonAdapterItem.ViewHolder> {

	private String data;


	public ButtonAdapterItem(String data) {
		this.data = data;
	}


	@Override
	public void onBindViewHolder(ViewHolder holder) {
		holder.bind(isSelected(), data);
	}


	@Override
	public ViewHolder createViewHolder(ViewGroup parent) {
		return new ButtonAdapterItem.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_button, parent, false));
	}


	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;

		ButtonAdapterItem that = (ButtonAdapterItem) o;

		return data != null ? data.equals(that.data) : that.data == null;
	}


	@Override
	public int hashCode() {
		return data != null ? data.hashCode() : 0;
	}


	public static class ViewHolder extends RecyclerView.ViewHolder {

		private Button mButton;

		public ViewHolder(View itemView) {
			super(itemView);

			mButton = (Button) itemView.findViewById(R.id.button);
		}


		public void bind(boolean selected, String text) {
			int color;

			if (selected) {
				color = itemView.getResources().getColor(R.color.background_selected);
			} else {
				color = itemView.getResources().getColor(R.color.background);
			}

			itemView.setBackgroundColor(color);
			mButton.setText(text);
		}
	}
}
