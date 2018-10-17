package com.whfp.anti_terrorism.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.whfp.anti_terrorism.R;
import com.whfp.anti_terrorism.utils.GlideApp;


public class ViewHolder {

	private SparseArray<View> mViews;

	private int mPositon;

	private View mConvertView;
	
	private Context context;

	public ViewHolder(Context context, ViewGroup parent, int layoutId,
                      int position)
	{
		this.context = context;
		this.mPositon = position;
		this.mViews = new SparseArray<View>();
		this.mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,false);
		mConvertView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		mConvertView.setTag(this);
		
	}

	public void setHight(int height){
		mConvertView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, height));
//		LayoutParams
	}

	public void setWidMax(){
		mConvertView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
//		LayoutParams
	}
	
	public static ViewHolder get(Context context, View convertView,
                                 ViewGroup parent, int layoutId, int position) {
		
		if (convertView == null) {
			return new ViewHolder(context, parent, layoutId, position);
		}else {
			ViewHolder holder = (ViewHolder) convertView.getTag();
			holder.mPositon = position;
			return holder;			
		}		
	}

	public <T extends View> T getView(int viewId) {
		View view = mViews.get(viewId);
		if (view == null) {
			view = mConvertView.findViewById(viewId);
			mViews.put(viewId, view);
		}	
		return (T) view;
	}
	
	public View getmConvertView() {
		return mConvertView;
	}
	
//	为Textview 设置 text的方法。
	public ViewHolder setText(int viewId,String text){
		TextView tv = getView(viewId);
		if(tv==null){
			Log.i("sadasd", "怎么就是空对象了？");
		}
		tv.setText(text);
		return this;
	}
	public ViewHolder setText(int viewId,int text){
		TextView tv = getView(viewId);
		tv.setText(text);
		return this;
	}

	public ViewHolder setTextColor(int viewId,int color){
		TextView tv = getView(viewId);
		tv.setTextColor(color);
		return this;
	}
	public ViewHolder setImage(int viewId,int imageId){
		ImageView iv = getView(viewId);
//		iv.setBackgroundResource(imageId);
		GlideApp.with(context).load(imageId).into(iv);
		return this;
	}
	

	public ViewHolder setImageSrc(int viewId,int imageId){
		ImageView iv = getView(viewId);
		iv.setImageResource(imageId);
		return this;
	}
	public ViewHolder setImage(int viewId,String url){
		ImageView iv = getView(viewId);
		GlideApp.with(context).load(url).placeholder(R.mipmap.banne_loding).into(iv);
		return this;
	}

	public ViewHolder setImageBg(int viewId,String url){
		final ImageView iv = getView(viewId);

		SimpleTarget<Drawable> simpleTarget = new SimpleTarget<Drawable>() {
			@Override
			public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
				iv.setBackground(resource);
			}
		};
		GlideApp.with(context).load(url).placeholder(R.mipmap.banne_loding).into(simpleTarget);
		return this;
	}
	
//	public ViewHolder setImageBg(ImageView view, String url){
//		Picasso.with(context).load(url).into(view);
//		return this;
//	}
	
	public ViewHolder setBg(int viewId,int imageId){
		View v = getView(viewId);
		v.setBackgroundResource(imageId);
		return this;
	}
	
//	开关
	public ViewHolder setSw(int viewId,boolean isChecked){
		Switch v = getView(viewId);
		v.setChecked(isChecked);
		return this;
	}
	
	public boolean getSw(int viewId){
		Switch v = getView(viewId);
		return v.isChecked();
	}
	
	public ViewHolder disView(int viewId){
		View v =getView(viewId);
		v.setVisibility(View.GONE);
		return this;
	}
	
	public ViewHolder setListener(int viewId,OnClickListener listener){
		View v =getView(viewId);
		v.setOnClickListener(listener);
		return this;
	}
	

	public ViewHolder showView(int viewId,boolean b){
		View v =getView(viewId);
		if (b) {
			v.setVisibility(View.VISIBLE);
		}else {
			v.setVisibility(View.GONE);
		}
		return this;
	}
}
