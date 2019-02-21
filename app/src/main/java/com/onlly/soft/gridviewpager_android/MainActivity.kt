package com.onlly.soft.gridviewpager_android

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private val titles = arrayOf(
        "美食",
        "电影",
        "酒店住宿",
        "休闲娱乐",
        "外卖",
        "自助餐",
        "KTV",
        "机票/火车票",
        "周边游",
        "美甲美睫",
        "火锅",
        "生日蛋糕",
        "甜品饮品",
        "水上乐园",
        "汽车服务",
        "美发",
        "丽人",
        "景点",
        "足疗按摩",
        "运动健身",
        "健身",
        "超市",
        "买菜",
        "今日新单",
        "小吃快餐",
        "面膜",
        "洗浴/汗蒸",
        "母婴亲子",
        "生活服务",
        "婚纱摄影",
        "学习培训",
        "家装",
        "结婚",
        "全部分配"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}
