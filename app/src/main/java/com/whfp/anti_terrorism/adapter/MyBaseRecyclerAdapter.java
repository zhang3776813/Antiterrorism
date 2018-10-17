package com.whfp.anti_terrorism.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView通用适配器
 * Created by 张明杨 on 2018-04-11-0011.
 */
public abstract class MyBaseRecyclerAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {

    private Context context;

    public MyBaseRecyclerAdapter(Context context, int layoutId) {
        this(context, layoutId, new ArrayList<T>());
    }

    public MyBaseRecyclerAdapter(Context context, int layoutResId, List<T> data) {
        super(layoutResId, data);
        this.context = context;
//        setOnItemClickListener(getListener());
//        setOnItemChildClickListener(getChildClickListener());
    }

//    //item点击事件
//    public abstract OnItemClickListener getListener();
//
//    //item内部子控件点击事件  （需要绑定ID）
//    public abstract OnItemChildClickListener getChildClickListener();

    /**
     * 获得Adapter
     * @param context                    上下文
     * @param layoutResId                Item布局
     * @param data                       数据源
     * @param Listener                   Item点击事件
     * @param childClickListener         Item子控件点击事件
     */
    public MyBaseRecyclerAdapter(Context context, int layoutResId, List<T> data, BaseQuickAdapter.OnItemClickListener Listener, BaseQuickAdapter.OnItemChildClickListener childClickListener) {
        super(layoutResId, data);
        this.context = context;
        setOnItemClickListener(Listener);
        setOnItemChildClickListener(childClickListener);
    }


}
