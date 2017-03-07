package com.artufimtcev.sadapter.sample;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.artufimtcev.sadapter.sample.databinding.ActivityMainBinding;
import com.artufimtcev.sadapter.sample.lazyloading.LazyloadingActivity;
import com.artufimtcev.sadapter.sample.listbuilder.ListBuilderActivity;
import com.artufimtcev.sadapter.sample.shuffle.ShuffleActivity;


public class MainActivity extends AppCompatActivity{

	private ActivityMainBinding mBinding;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
	}


	public void onListBuilderClick(View v) {
		startActivity(ListBuilderActivity.newIntent(this));
	}


	public void onLazyloadingClick(View v) {
		startActivity(LazyloadingActivity.newIntent(this));
	}


	public void onShuffleClick(View v) {
		startActivity(ShuffleActivity.newIntent(this));
	}
}
