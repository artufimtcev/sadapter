package com.artufimtcev.sadapter.sample.lazyloading;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


public class LazyloadingActivity extends AppCompatActivity {

	public static Intent newIntent(Context context) {
		return new Intent(context, LazyloadingActivity.class);
	}


	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}
}
