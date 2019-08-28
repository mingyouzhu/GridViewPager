package com.onlly.soft.gridviewpager_android

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.onlly.soft.gridviewpager.GridViewPager
import com.onlly.soft.gridviewpager.ImageLoader
import com.onlly.soft.gridviewpager.Model

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun init() {
        val domain = "http://www.520mj.net:800"
        val models: ArrayList<Model<String>> = ArrayList()
        for(i in 0 .. 30)models.add(Model("图片1", "描述1", "$domain/StaticFiles/GridNineImages/4d142d608e474b00a7e252036a8ed12b.png"))
        val gridNine = findViewById<GridViewPager<String>>(R.id.mGridViewPager)
        // 设置图片加载引擎
        gridNine.setImageLoader(object:ImageLoader<String>{
            override fun load(context: Context, icon: String, imageView: ImageView) {
                Glide.with(context)
                    .load(icon)
                    .override(120,120)
                    .into(imageView)
            }
        })
        // 初始化
        gridNine.init(models)
    }
}