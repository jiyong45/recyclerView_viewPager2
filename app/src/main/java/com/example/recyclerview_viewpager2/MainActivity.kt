package com.example.recyclerview_viewpager2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.gson.Gson
import java.io.IOException

class MainActivity : AppCompatActivity() {

    val jsonString by lazy { getJsonDataFromAsset() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (jsonString != null) {
            val data = Gson().fromJson(jsonString, JsonData::class.java)
            val tabList = data.favoriteBrandM?.tabList

            val mAdapter = RecycleAdapter(this, tabList as List<JsonData.FavoriteBrandM.Tab>?)
            val recycleView = findViewById<RecyclerView>(R.id.tabRecycle)
            recycleView.adapter = mAdapter
            mAdapter.setItemClickListener(object : RecycleAdapter.ItemClickListener {
                override fun onClick(view: View, position: Int) {
                    Log.d("tabList33", "${position} 번 리스트 선택 ")
                    val mAdapter2 = ViewPagerAdapter(this@MainActivity, tabList?.getOrNull(position)?.list as List<JsonData.FavoriteBrandM.Tab.Product>?)
                    val viewAdapter = findViewById<ViewPager2>(R.id.productView)
                    viewAdapter.adapter = mAdapter2

                }
            })

            val mAdapter2 = ViewPagerAdapter(this@MainActivity, tabList?.getOrNull(0)?.list as List<JsonData.FavoriteBrandM.Tab.Product>?)
            val viewAdapter = findViewById<ViewPager2>(R.id.productView)
            viewAdapter.adapter = mAdapter2

        }


    }

    fun getJsonDataFromAsset() : String {

        val jsonString: String
        try{
            jsonString =this.assets.open("jsons/item_json.json").bufferedReader().use{it.readText()}
        } catch (ioExceiption: IOException){
            ioExceiption.printStackTrace()
            return ""
        }
        return jsonString
    }
}