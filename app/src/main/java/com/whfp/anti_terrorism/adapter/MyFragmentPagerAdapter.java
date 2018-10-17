package com.whfp.anti_terrorism.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 描述：一个通用的Fragment适配器
 * Created by 张明杨 on 2018-04-19-0019.
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    //Fragment集合
    private List<Fragment> mFragmentList = null;
    //titles是给TabLayout设置title用的
    private String[] titles;

    public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> mFragmentList, String[] titles) {
        super(fm);
        this.mFragmentList = mFragmentList;
        this.titles = titles;
    }

    /**
     * 获取索引位置的Fragment
     *
     * @param position 索引
     * @return fragment
     */
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position < mFragmentList.size()) {
            fragment = mFragmentList.get(position);
        } else {
            fragment = mFragmentList.get(0);
        }
        return fragment;

    }

    /**
     * 获取数量
     *
     * @return
     */
    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    /**
     * 获取索引位置的Title
     *
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        if (titles != null && titles.length > 0)
            return titles[position];
        return null;
    }
}
