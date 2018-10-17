package com.whfp.anti_terrorism.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.whfp.anti_terrorism.R;


/**s
 * GridView菜单适配器
 * Created by 张明杨 on 2018-03-19-0019.
 */
public class GridAdapter extends BaseAdapter {
    private Context context;

    public int[] texts;

    public int[] imgs;

    public GridAdapter(Context mContext, int[] texts, int[] imgs) {
        super();
        this.context = mContext;
        this.texts = texts;
        this.imgs = imgs;
    }

    @Override
    public int getCount() {
        return texts.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodler hodler;
        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_item_menu, null);
            hodler = new ViewHodler();

            hodler.iv_img = (ImageView) convertView.findViewById(R.id.iv_img);
            hodler.tv_text = (TextView) convertView.findViewById(R.id.tv_text);

            convertView.setTag(hodler);
        } else {
            hodler = (ViewHodler) convertView.getTag();
        }

        hodler.iv_img.setImageResource(imgs[position]);
        hodler.tv_text.setText(texts[position]);
        return convertView;
    }

    class ViewHodler {
        ImageView iv_img;
        TextView tv_text;
    }

}
