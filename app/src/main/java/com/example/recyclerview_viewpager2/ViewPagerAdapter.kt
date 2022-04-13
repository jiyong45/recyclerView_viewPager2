package com.example.recyclerview_viewpager2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ViewPagerAdapter (private val context: Context, private val data2List: List<JsonData.FavoriteBrandM.Tab.Product>?) : RecyclerView.Adapter<ViewPagerAdapter.PagerViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycle_item, parent, false)
        return PagerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data2List?.size ?: 0
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        (holder as? PagerViewHolder)?.let { it ->
            it.bind(data2List?.get(position))
        }
    }

    inner class PagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val recycleImg = itemView.findViewById<ImageView>(R.id.recycleImg)
        val star = itemView.findViewById<ImageView>(R.id.star)
        val recycleTitle = itemView.findViewById<TextView>(R.id.recycleTitle)
        val recycleDescipt = itemView.findViewById<TextView>(R.id.recycleDescipt)

        fun bind(productList: JsonData.FavoriteBrandM.Tab.Product?) {
            Glide.with(context).load(productList?.imgPath).into(recycleImg)
            recycleTitle.setText(productList?.brandNm)
            recycleDescipt.setText(productList?.goodsNm)
            if(productList?.likeYn == false){
                star.setImageResource(R.drawable.ic_star_black)
            }else {
                star.setImageResource(R.drawable.ic_star_red)
            }

        }


    }

}