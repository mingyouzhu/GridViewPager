package com.onlly.soft.gridviewpager;

import android.content.Context
import android.widget.ImageView

interface ImageLoader<T> {
    fun load(context:Context,icon:T,imageView:ImageView)
}