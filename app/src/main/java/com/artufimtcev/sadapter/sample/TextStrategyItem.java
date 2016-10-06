package com.artufimtcev.sadapter.sample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class TextStrategyItem extends SelectableStrategyItem<TextStrategyItem.ViewHolder> {

	private String data;

	public TextStrategyItem(String data) {
		this.data = data;
	}


	@Override
	public void onBindViewHolder(ViewHolder holder) {
		holder.bind(isSelected(), data);
	}


	@Override
	public ViewHolder createViewHolder(ViewGroup parent) {
		return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text, parent, false));
	}


	public static class ViewHolder extends RecyclerView.ViewHolder {

		private TextView mTextView;

		public ViewHolder(View itemView) {
			super(itemView);

			mTextView = (TextView) itemView.findViewById(R.id.text);
		}


		public void bind(boolean selected, String text) {
			int color;

			if (selected) {
				color = itemView.getResources().getColor(R.color.background_selected);
			} else {
				color = itemView.getResources().getColor(R.color.background);
			}

			itemView.setBackgroundColor(color);

			mTextView.setText(text);
		}
	}
}