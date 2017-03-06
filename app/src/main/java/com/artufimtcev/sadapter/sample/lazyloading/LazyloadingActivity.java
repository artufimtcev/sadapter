package com.artufimtcev.sadapter.sample.lazyloading;


import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.artufimtcev.sadapter.AdapterItem;
import com.artufimtcev.sadapter.sample.R;
import com.artufimtcev.sadapter.sample.databinding.ActivityLazyloadingBinding;
import com.artufimtcev.sadapter.sample.listbuilder.ButtonAdapterItem;
import com.artufimtcev.sadapter.sample.listbuilder.ImageViewItem;
import com.artufimtcev.sadapter.sample.listbuilder.TextAdapterItem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.schedulers.Schedulers;


public class LazyloadingActivity extends AppCompatActivity {

	private ActivityLazyloadingBinding mBinding;
	private LazyloadingAdapter mAdapter = new LazyloadingAdapter();

	private Subscription mCurrentCall;


	public static Intent newIntent(Context context) {
		return new Intent(context, LazyloadingActivity.class);
	}


	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.mBinding = DataBindingUtil.setContentView(this, R.layout.activity_lazyloading);
		mBinding.recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
		mBinding.recycler.setAdapter(mAdapter);

		loadData();

		mBinding.recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
			@Override
			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
				super.onScrolled(recyclerView, dx, dy);

				if(mCurrentCall == null) {
					loadData();
				}
			}
		});
	}


	private void loadData() {
		mAdapter.showProgress();
		this.mCurrentCall = this.mockLoadDataObservable()
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Action1<List<AdapterItem<? extends RecyclerView.ViewHolder>>>() {

					@Override
					public void call(List<AdapterItem<? extends RecyclerView.ViewHolder>> data) {
						onDataLoaded(data);
					}
				});
	}


	private void onDataLoaded(List<AdapterItem<? extends RecyclerView.ViewHolder>> data) {
		mAdapter.addAll(data);
		mAdapter.hideProgress();

		mCurrentCall.unsubscribe();
		mCurrentCall = null;
	}


	private Observable<List<AdapterItem<? extends RecyclerView.ViewHolder>>> mockLoadDataObservable() {
		return Observable.fromCallable(new Func0<List<AdapterItem<? extends RecyclerView.ViewHolder>>>() {
			@Override
			public List<AdapterItem<? extends RecyclerView.ViewHolder>> call() {
				List<AdapterItem<? extends RecyclerView.ViewHolder>> items = new ArrayList<>();

				items.add(new TextAdapterItem("Text 1"));
				items.add(new TextAdapterItem("Text 2"));
				items.add(new TextAdapterItem("Text 3"));
				items.add(new TextAdapterItem("Text 4"));
				items.add(new TextAdapterItem("Text 5"));

				items.add(new ImageViewItem());
				items.add(new ImageViewItem());

				items.add(new ButtonAdapterItem("Button 1"));

				return items;
			}
		}).delay(2, TimeUnit.SECONDS);
	}
}
