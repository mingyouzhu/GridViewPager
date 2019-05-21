package com.onlly.soft.gridviewpager;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class GridViewPager<ResType> extends RelativeLayout{
    private boolean hasCustomOval = false;
    private LayoutInflater inflater;
    private Context mContext;
    private ViewPager mPager;
    private LinearLayout mLlDot;
    private List<Model<ResType>> mData;

    private List<GridView> mPagerList;
    private GridItemClickListener gridItemClickListener;
    private GridItemLongClickListener gridItemLongClickListener;
    private ImageLoader<ResType> mImageLoader;

    // 总的页数 计算得出
    private int pageCount;

    // 每一页显示的个数 可设置
    private int pageSize = 10;

    // 当前显示的是第几页
    private int curIndex = 0;

    private int mNumColumns = 4;
    private boolean mShowIndicator = true;

    public GridViewPager(Context context) {
        super(context);
        mContext = context;
        initView(context,null);
    }

    public GridViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView(context,attrs);
    }

    public GridViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView(context,attrs);
    }

    private void initView(Context context,AttributeSet attrs) {
        if(attrs != null){
            TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable._GridViewPager);
            mShowIndicator = typedArray.getBoolean(R.styleable._GridViewPager_showIndicator,mShowIndicator);
            mNumColumns = typedArray.getInteger(R.styleable._GridViewPager_numColumns,mNumColumns);
            pageSize = typedArray.getInteger(R.styleable._GridViewPager_pageSize,pageSize);
            typedArray.recycle();
        }
        inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.view, this);
        mPager = view.findViewById(R.id.viewpager);
        mLlDot = view.findViewById(R.id.ll_dot);
        mLlDot.setVisibility(mShowIndicator ? VISIBLE : GONE);
    }

    public void setNumColumns(int num){
        mNumColumns = num;
    }

    public void setShowIndicator(boolean show){
        mShowIndicator = show;
    }

    // 必须作为最后一步
    public GridViewPager init(List<Model<ResType>> list) {
        mData = list;
        //总的页数=总数/每页数量，并取整
        pageCount = (int) Math.ceil(mData.size() * 1.0 / pageSize);
        mPagerList = new ArrayList<>();
        for (int i = 0; i < pageCount; i++) {
            //每个页面都是inflate出一个新实例
            GridView gridView = (GridView) inflater.inflate(R.layout.gridview, mPager, false);
            GridViewAdapter<ResType> adapter = new GridViewAdapter(mContext, mData, i, pageSize);
            adapter.setImageLoader(mImageLoader);
            gridView.setAdapter(adapter);
            mPagerList.add(gridView);
            gridView.setNumColumns(mNumColumns);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                    if (gridItemClickListener != null) {
                        int position = pos + curIndex * pageSize;
                        gridItemClickListener.click(position, mData.get(position));
                    }
                }
            });
            gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int pos, long id) {
                    if (gridItemLongClickListener != null) {
                        int position = pos + curIndex * pageSize;
                        gridItemLongClickListener.click(position, mData.get(position));
                        return true;
                    }
                    return false;
                }
            });
        }
        mPager.setAdapter(new ViewPagerAdapter(mPagerList));
        //设置圆点
        if (!hasCustomOval) setOvalLayout();
        return this;
    }

    // 设置自定义圆点
    public void setOvalLayout(View view, ViewPager.OnPageChangeListener listener) {
        hasCustomOval = true;
        mLlDot.removeAllViews();
        mLlDot.addView(view);
        mPager.addOnPageChangeListener(listener);
    }

    // 设置圆点
    private void setOvalLayout() {
        mLlDot.removeAllViews();
        for (int i = 0; i < pageCount; i++) {
            mLlDot.addView(inflater.inflate(R.layout.dot, null));
        }
        // 默认显示第一页
        View view = mLlDot.getChildAt(0).findViewById(R.id.v_dot);
        view.setBackgroundResource(R.drawable.dot_selected);
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageSelected(int position) {
                // 取消圆点选中
                View view = mLlDot.getChildAt(curIndex).findViewById(R.id.v_dot);
                view.setBackgroundResource(R.drawable.dot_normal);
                // 圆点选中
                view = mLlDot.getChildAt(position).findViewById(R.id.v_dot);
                view.setBackgroundResource(R.drawable.dot_selected);
                curIndex = position;
            }

            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

    public GridViewPager setGridItemClickListener(GridItemClickListener listener) {
        gridItemClickListener = listener;
        return this;
    }

    public GridViewPager setGridItemLongClickListener(GridItemLongClickListener listener) {
        gridItemLongClickListener = listener;
        return this;
    }

    public List<GridView> getmPagerList() {
        return mPagerList;
    }

    public int getPageCount() {
        return pageCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public GridViewPager setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public int getCurIndex() {
        return curIndex;
    }

    public void setImageLoader(ImageLoader<ResType> loader){
        this.mImageLoader = loader;
    }
}
