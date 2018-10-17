package com.whfp.anti_terrorism.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.whfp.anti_terrorism.R;
import com.whfp.anti_terrorism.adapter.MAdapter;
import com.whfp.anti_terrorism.adapter.ViewHolder;
import com.whfp.anti_terrorism.basic.BasicActivity;
import com.whfp.anti_terrorism.bean.Constants;
import com.whfp.anti_terrorism.bean.MenuBean;
import com.whfp.anti_terrorism.factory.MyFactory;
import com.whfp.anti_terrorism.utils.GlideImageLoader;
import com.whfp.anti_terrorism.utils.StatusBarUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 * 主页
 */
@ContentView(R.layout.activity_main)
public class MainActivity extends BasicActivity {

    //图片轮播控件
    @ViewInject(R.id.banner)
    private Banner banner;

    //九宫格菜单控件
    @ViewInject(R.id.gv_menu)
    private GridView gv_menu;

    //轮播图片地址集合
    private List<Integer> images;

    //九宫格菜单数据源
    private List<MenuBean> list_data;

    //九宫格菜单适配器
    private MAdapter<MenuBean> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置沉浸式状态栏
        StatusBarUtils.with(this)
                .init();
    }

    @Override
    protected void init() {
        super.init();
        initBunner();
        initGridMenu();
    }

    /**
     * 初始化图片轮播
     */
    private void initBunner() {
        images = MyFactory.getBaseListDatas(Constants.IMAGE_LIST);
        //设置Banner风格（圆形指示器）
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Default);
//        //设置标题集合（当banner样式有显示title时）
//        banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    /**
     * 初始化九宫格菜单
     */
    private void initGridMenu() {
        list_data = MyFactory.getBaseListDatas(Constants.GRID_LIST);
        adapter = new MAdapter<MenuBean>(context, list_data, R.layout.item_grid_menu) {
            @Override
            public void convert(ViewHolder holder, int position) {
                holder.setImage(R.id.iv_img, list_data.get(position).getImg());
                holder.setText(R.id.tv_text, list_data.get(position).getText());
            }
        };
        gv_menu.setAdapter(adapter);
        gv_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MyFactory.ListClick(context, Constants.GRID_LIST, i, null);
            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        //开始轮播
        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
    }

}
