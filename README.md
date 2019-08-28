# GridViewPager
防京东滑动式九宫格

# 引入
```
dependencies {
	        implementation 'com.github.mingyouzhu:GridViewPager:1.1'
}
```

# 布局文件 activity_main.xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
              xmlns:app="http://schemas.android.com/apk/res-auto"
              android:layout_width="match_parent" android:layout_height="match_parent"
              android:orientation="vertical">

    <com.onlly.soft.gridviewpager.GridViewPager
            android:id="@+id/mGridViewPager"
            android:layout_height="200dp"
            android:background="#FFFFFF"
            android:layout_width="match_parent"
            android:layout_marginTop="10dp"
            app:showIndicator="true"
            app:numColumns="5"
            app:pageSize="10"
            app:itemPaddingTop="15"
            app:itemPaddingBottom="5"/>

</LinearLayout>

# MainActivity.kt
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