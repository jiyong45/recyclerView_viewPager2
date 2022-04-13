package com.example.recyclerview_viewpager2

import android.app.Notification
import android.content.Context
import android.graphics.Typeface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class RecycleAdapter(private val context: Context, private val data2List : List<JsonData.FavoriteBrandM.Tab>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var prevPosition : Int = 0

    inner class ItemViewHolder(itemView: View, parent : ViewGroup): RecyclerView.ViewHolder(itemView){
        val tabTitle = itemView.findViewById<TextView>(R.id.tabTitle)


        fun bind(tab_list : JsonData.FavoriteBrandM.Tab?){
            tabTitle.setText(tab_list?.tabTitle ?: "")
            tabTitle.setTypeface(null, Typeface.NORMAL)
            itemView.setOnClickListener {
                tabTitle.setTypeface(null, Typeface.BOLD)
                this@RecycleAdapter.notifyItemChanged(prevPosition)
                prevPosition = adapterPosition
                Toast.makeText(itemView.context, "test >> ${tab_list?.tabTitle ?: ""}", Toast.LENGTH_SHORT).show()
                Log.d("tabList", data2List?.getOrNull(adapterPosition)?.list.toString())
                itemClickListener.onClick(it, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int): RecyclerView.ViewHolder{
        val view = LayoutInflater.from(context).inflate(R.layout.menu_item, parent, false)
        return ItemViewHolder(view,parent)
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int){
        (holder as? ItemViewHolder)?.let {
            it.bind(data2List?.getOrNull(position))
        }
    }

    interface ItemClickListener{
        fun onClick(view : View,position : Int )
    }

    private lateinit var itemClickListener: ItemClickListener

    fun setItemClickListener(itemClickListener: ItemClickListener){
        this.itemClickListener = itemClickListener
    }


    override fun getItemCount(): Int {
        return data2List?.size ?: 0
    }

}
