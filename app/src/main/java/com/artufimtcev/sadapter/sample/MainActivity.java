package com.artufimtcev.sadapter.sample;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.artufimtcev.sadapter.sample.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

	private final SampleAdapter mSampleAdapter = new SampleAdapter();
	private ActivityMainBinding mBinding;


	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

		RecyclerView recycler = mBinding.recycler;
		recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
		recycler.setAdapter(mSampleAdapter);

		mBinding.addText.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mSampleAdapter.addText("This is text!");
			}
		});

		mBinding.addButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mSampleAdapter.addButton("This is button!");
			}
		});

		mBinding.addImage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mSampleAdapter.addImage();
			}
		});

		mBinding.addProgress.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				addProgress();
			}
		});
	}





	private void addProgress() {
	}
}
