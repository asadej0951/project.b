package com.example.projectb.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectb.R
import com.example.projectb.model.ResponsePost
import com.squareup.picasso.Picasso

class AdapterPost (val ct : Context, private var mDataPost: ArrayList<ResponsePost>): RecyclerView.Adapter<ViewHolderPost>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPost {
        return ViewHolderPost(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_post,parent,false
            )
        )
    }
    override fun getItemCount() = mDataPost.size

    override fun onBindViewHolder(holder: ViewHolderPost, position: Int) {
        if (mDataPost[position].p_message.length <50){
            holder.messageitem.text=mDataPost.get(position).p_message
        }
        else{
            holder.messageitem.text=mDataPost.get(position).p_message.substring(0,50)+"..."
        }
        Picasso.get().load("http://192.168.1.9:4000/image/"+mDataPost[position].img).into(holder.u_img)
        holder.usernamePost.text = mDataPost.get(position).u_name
        holder.timePost.text = mDataPost.get(position).p_time
    }
}
class ViewHolderPost(item:View):RecyclerView.ViewHolder(item){
    val u_img : ImageView = item.findViewById(R.id.img_userItem_post)
    val usernamePost : TextView = item.findViewById(R.id.tv_username_PostItem)
    val messageitem : TextView = item.findViewById(R.id.tv_messageItem)
    val timePost : TextView = item.findViewById(R.id.timeItem_Post)
}