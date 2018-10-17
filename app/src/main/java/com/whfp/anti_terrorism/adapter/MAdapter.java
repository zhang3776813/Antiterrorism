package com.whfp.anti_terrorism.adapter;

import android.content.Context;

import com.whfp.anti_terrorism.basic.BasicAdapter;

import java.util.List;

public abstract class MAdapter<T> extends BasicAdapter<T> {



	public MAdapter(Context context, List<T> list, int id){
		super(context, list,id);
	}

	@Override
	public abstract void convert(ViewHolder holder, int position);

}
