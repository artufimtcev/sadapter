package com.artufimtcev.sadapter.sample.lazyloading;


import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.artufimtcev.sadapter.sample.R;
import com.artufimtcev.sadapter.sample.databinding.ActivityLazyloadingBinding;


public class LazyloadingActivity extends AppCompatActivity {

	public static Intent newIntent(Context context) {
		return new Intent(context, LazyloadingActivity.class);
	}


	private ActivityLazyloadingBinding mBinding;


	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.mBinding = DataBindingUtil.setContentView(this, R.layout.activity_lazyloading);
	}


	private void loadData() {

	}
}
