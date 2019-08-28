package com.onlly.soft.gridviewpager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class GridViewAdapter<ResType> extends BaseAdapter {
    private Context mContext;
    private List<Model<ResType>> mData;
    private LayoutInflater inflater;
    private ImageLoader<ResType> mImageLoader;

    /**
     * 页数下标,从0开始(当前是第几页)
     */
    private int curIndex;
    /**
     * 每一页显示的个数
     */
    private int pageSize;

    private int itemPaddingTop = 0,itemPaddingBottom = 0;

    public GridViewAdapter(Context context, List<Model<ResType>> mData, int curIndex, int pageSize,int itemPaddingTop,int itemPaddingBottom) {
        mContext = context;
        inflater = LayoutInflater.from(context);
        this.mData = mData;
        this.curIndex = curIndex;
        this.pageSize = pageSize;
        this.itemPaddingTop = itemPaddingTop;
        this.itemPaddingBottom = itemPaddingBottom;
    }

    /**
     * 先判断数据集的大小是否足够显示满本页？mData.size() > (curIndex+1)*pageSize,
     * 如果够，则直接返回每一页显示的最大条目个数pageSize,
     * 如果不够，则有几项返回几,(mData.size() - curIndex * pageSize);(也就是最后一页的时候就显示剩余item)
     */
    @Override
    public int getCount() {
        return mData.size() > (curIndex + 1) * pageSize ? pageSize : (mData.size() - curIndex * pageSize);

    }

    @Override
    public Model getItem(int position) {
        return mData.get(position + curIndex * pageSize);
    }

    @Override
    public long getItemId(int position) {
        return position + curIndex * pageSize;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_gridview, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvTitle = convertView.findViewById(R.id.tvTitle);
            viewHolder.tvDescription = convertView.findViewById(R.id.tvDesc);
            viewHolder.ivHead = convertView.findViewById(R.id.ivHead);
            convertView.setPadding(0,itemPaddingTop,0,itemPaddingBottom);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        /**
         * 在给View绑定显示的数据时，计算正确的position = position + curIndex * pageSize，
         */
        int pos = position + curIndex * pageSize;
        viewHolder.tvTitle.setText(mData.get(pos).getName());
        viewHolder.tvDescription.setText(mData.get(pos).getDescription());
        ResType icon = mData.get(pos).getIcon();
        mImageLoader.load(mContext,icon,viewHolder.ivHead);
        return convertView;
    }

    private class ViewHolder {
        TextView tvTitle,tvDescription;
        ImageView ivHead;
    }

    public void setImageLoader(ImageLoader loader){
        this.mImageLoader = loader;
    }
}