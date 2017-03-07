package com.artufimtcev.sadapter.sample.shuffle;


import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.CheckBox;

import com.artufimtcev.sadapter.sample.R;
import com.artufimtcev.sadapter.sample.adapteritem.TextAdapterItem;
import com.artufimtcev.sadapter.sample.databinding.ActivityShuffleBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ShuffleActivity extends AppCompatActivity {

	public static Intent newIntent(Context context) {
		return new Intent(context, ShuffleActivity.class);
	}

	private static final int ITEMS_COUNT = 10;

	private ActivityShuffleBinding mBinding;

	private final ShuffleAdapter mAdapter = new ShuffleAdapter();
	private final List<String> mData = new ArrayList<>();

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.mBinding = DataBindingUtil.setContentView(this, R.layout.activity_shuffle);
		mBinding.recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
		mBinding.recycler.setAdapter(mAdapter);

		for(int i = 0; i < ITEMS_COUNT; i++) {
			mData.add("Item #" + (i + 1));
		}

		onShuffleClick(null);
	}


	public void onShuffleClick(View v) {
		boolean useDiffUtil = mBinding.useDiffutil.isChecked();

		Collections.shuffle(mData);

		if(useDiffUtil) {
			mAdapter.setDataUsingDiffUtil(mData);
		} else {
			mAdapter.setData(mData);
		}
	}
}
