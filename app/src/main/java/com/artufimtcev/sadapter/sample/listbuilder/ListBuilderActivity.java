package com.artufimtcev.sadapter.sample.listbuilder;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.artufimtcev.sadapter.sample.R;
import com.artufimtcev.sadapter.sample.databinding.ActivityListBuilderBinding;


public class ListBuilderActivity extends AppCompatActivity {

	public static Intent newIntent(Context context) {
		return new Intent(context, ListBuilderActivity.class);
	}

	private final ListBuilderAdapter mListBuilderAdapter = new ListBuilderAdapter();
	private ActivityListBuilderBinding mBinding;


	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mBinding = DataBindingUtil.setContentView(this, R.layout.activity_list_builder);

		RecyclerView recycler = mBinding.recycler;
		recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
		recycler.setAdapter(mListBuilderAdapter);

		mBinding.addText.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mListBuilderAdapter.addText("This is text!");
			}
		});

		mBinding.addButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mListBuilderAdapter.addButton("This is button!");
			}
		});

		mBinding.addImage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mListBuilderAdapter.addImage();
			}
		});

		mBinding.addProgress.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
			}
		});
	}
}
