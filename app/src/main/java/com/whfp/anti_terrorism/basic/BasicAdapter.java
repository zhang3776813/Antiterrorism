package com.whfp.anti_terrorism.basic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.whfp.anti_terrorism.adapter.ViewHolder;

import java.util.List;

public abstract class BasicAdapter<T> extends BaseAdapter {
	
	protected Context mContext;
	protected List<T> mDatas;
	protected LayoutInflater mInflater;
	protected int itemLayoutId;
	
	
	
	protected int currentPostion = 0;
	public int getCurrentPostion() {
		return currentPostion;
	}

	public void setCurrentPostion(int currentPostion) {
		this.currentPostion = currentPostion;
	}

	
	public List<T> getmDatas() {
		return mDatas;
	}

	public void setmDatas(List<T> mDatas) {
		this.mDatas = mDatas;
		notifyDataSetChanged();
	}

	public BasicAdapter(Context context, List<T> list, int itemLayoutId) {
		this.mContext = context;
		mInflater = LayoutInflater.from(context);
		this.mDatas = list;
		this.itemLayoutId = itemLayoutId;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mDatas.size();
	}

	@Override
	public T getItem(int position) {
		// TODO Auto-generated method stub
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		
		ViewHolder holder = new ViewHolder(mContext, (ViewGroup)convertView, itemLayoutId, position);
		
		convert(holder, position);
		
		return holder.getmConvertView();
	}
	
	public abstract void convert(ViewHolder holder,int postion);
	
	
}
