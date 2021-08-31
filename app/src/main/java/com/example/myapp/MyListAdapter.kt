package com.samapp.week3

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class MyListAdapter
    (private val listdata: List<MyListData>?) :
    RecyclerView.Adapter<MyListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem: View = layoutInflater.inflate(R.layout.itemlayout, parent, false)
        return ViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val myListData = listdata!![position]
        holder.textView.text = listdata[position].description
        holder.imageView.setImageResource(listdata[position].imgId)
        holder.textView1.text = listdata[position].email
        holder.textView2.text = listdata[position].radioGroup
        holder.time.text = listdata[position].time
        holder.imageView.setOnClickListener { view ->


            val intent = Intent(view.context, ProfileActivity::class.java)
            intent.putExtra("name",myListData.description)
            intent.putExtra("img",myListData.imgId)
            intent.putExtra("email",myListData.email)
            intent.putExtra("gender",myListData.radioGroup)
            intent.putExtra("time",myListData.time)
            intent.putExtra("pos",position)
             view.context.startActivity(intent)
        }
    }
   override fun getItemCount(): Int {
        return listdata!!.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView
        var textView1: TextView
        var textView2: TextView
        var textView: TextView
      var time:TextView
        private var relativeLayout: RelativeLayout

        init {
            imageView = itemView.findViewById<View>(R.id.item_image) as ImageView
            textView = itemView.findViewById<View>(R.id.item_title) as TextView
            textView1 = itemView.findViewById<View>(R.id.item_detail) as TextView
            textView2 = itemView.findViewById<View>(R.id.item_detail2) as TextView
            time = itemView.findViewById<View>(R.id.time) as TextView
            relativeLayout = itemView.findViewById<View>(R.id.relativeLayout) as RelativeLayout
        }

    }


}