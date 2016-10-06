package com.artufimtcev.sadapter.sample;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;



public class MainActivity extends AppCompatActivity {

	private RecyclerView mRecycler;
	private SampleAdapter mAdapter;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mRecycler = (RecyclerView) findViewById(R.id.recycler);
		mRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
		mAdapter = new SampleAdapter();
		mRecycler.setAdapter(mAdapter);


		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				mAdapter.updateTexts();
			}
		}, 5000);
	}
}
