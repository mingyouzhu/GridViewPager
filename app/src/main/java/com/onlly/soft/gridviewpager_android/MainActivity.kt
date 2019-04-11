package com.onlly.soft.gridviewpager_android

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.onlly.soft.gridviewpager.GridViewPager
import com.onlly.soft.gridviewpager.ImageLoader
import com.onlly.soft.gridviewpager.Model
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_init.setOnClickListener { init() }
    }

    private fun init() {
        val domain = "http://www.520mj.net:800"
        val models: ArrayList<Model<String>> = ArrayList()
        models.add(Model<String>("图片1", "描述1", "$domain/StaticFiles/GridNineImages/4d142d608e474b00a7e252036a8ed12b.png"))
        models.add(Model<String>("图片1", "描述1", "$domain/StaticFiles/GridNineImages/4d142d608e474b00a7e252036a8ed12b.png"))
        models.add(Model<String>("图片1", "描述1", "$domain/StaticFiles/GridNineImages/4d142d608e474b00a7e252036a8ed12b.png"))
        models.add(Model<String>("图片1", "描述1", "$domain/StaticFiles/GridNineImages/4d142d608e474b00a7e252036a8ed12b.png"))
        models.add(Model<String>("图片1", "描述1", "$domain/StaticFiles/GridNineImages/4d142d608e474b00a7e252036a8ed12b.png"))
        models.add(Model<String>("图片1", "描述1", "$domain/StaticFiles/GridNineImages/4d142d608e474b00a7e252036a8ed12b.png"))
        models.add(Model<String>("图片1", "描述1", "$domain/StaticFiles/GridNineImages/4d142d608e474b00a7e252036a8ed12b.png"))
        models.add(Model<String>("图片1", "描述1", "$domain/StaticFiles/GridNineImages/4d142d608e474b00a7e252036a8ed12b.png"))
        models.add(Model<String>("图片1", "描述1", "$domain/StaticFiles/GridNineImages/4d142d608e474b00a7e252036a8ed12b.png"))
        models.add(Model<String>("图片1", "描述1", "$domain/StaticFiles/GridNineImages/4d142d608e474b00a7e252036a8ed12b.png"))
        models.add(Model<String>("图片1", "描述1", "$domain/StaticFiles/GridNineImages/4d142d608e474b00a7e252036a8ed12b.png"))
        val gridNine = findViewById<GridViewPager<String>>(R.id.mGridViewPager)
        gridNine.setImageLoader(object:ImageLoader<String>{
            override fun load(context: Context, icon: String, imageView: ImageView) {
                Glide.with(context)
                    .load(icon)
                    .override(120,120)
                    .into(imageView)
            }
        })
        gridNine.pageSize = 7
        gridNine.init(models)
    }
}
