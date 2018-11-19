package com.whfp.anti_terrorism.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dvr.net.RemoteFileInfo;
import com.whfp.anti_terrorism.R;

import java.util.List;

/**
 * Created by Lake on 2016/11/2.
 */

public class RetrievalListAdpater extends BaseAdapter {
    private final static String TAG="RetrievalListAdpater";
    private Context mContext;
    private List<RemoteFileInfo> mData;

    public RetrievalListAdpater(Context mContext, List<RemoteFileInfo> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public RemoteFileInfo getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view= LayoutInflater.from(mContext).inflate(R.layout.retieval_item,null);
            holder = new ViewHolder();
            holder.filename=(TextView)view.findViewById(R.id.list_item_tv);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        holder.filename.setText(fileInfostoName(mData.get(i)));
        return view;
    }
    public static class ViewHolder {
        public TextView filename;
    }
    public String fileInfostoName(RemoteFileInfo remoteFileInfo){
        String time=remoteFileInfo.FileTime;
        String starttime=time.substring(8,14);
        String endtime=time.substring(23,29);
        starttime=starttime.substring(0,2)+":"+starttime.substring(2,4)+":"+starttime.substring(4,6);
        endtime=endtime.substring(0,2)+":"+endtime.substring(2,4)+":"+endtime.substring(4,6);
        time=starttime+"-"+endtime;
        return time+"  CH "+ String.valueOf(remoteFileInfo.nChannel+1);
    }
}
