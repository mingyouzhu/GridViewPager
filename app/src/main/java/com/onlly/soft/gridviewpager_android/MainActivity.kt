package com.onlly.soft.gridviewpager_android

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.onlly.soft.gridviewpager.GridViewPager
import com.onlly.soft.gridviewpager.ImageLoader
import com.onlly.soft.gridviewpager.Model

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val models = ArrayList<Model<String>>()
        for(i in 0 .. 10)models.add(Model(
            "淘宝",
            "最高返佣",
            "http://ht-dev.oss-cn-beijing.aliyuncs.com/images/20190330/6FJrc5dNwy7DFpsFebCWvffwSMLEUoGr8UgrW6hE.png"))

        val mGridViewPager:GridViewPager<String> = findViewById(R.id.mGridViewPager)
        mGridViewPager.setImageLoader(object:ImageLoader<String>{
            override fun load(context: Context, icon: String, imageView: ImageView) {
                Glide.with(context).load(icon).override(100,100).into(imageView)
            }
        })
        mGridViewPager.pageSize = 7
        mGridViewPager.init(models)

    }
}