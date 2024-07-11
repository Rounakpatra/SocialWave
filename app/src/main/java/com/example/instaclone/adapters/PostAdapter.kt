package com.example.instaclone.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.instaclone.Models.PostModel
import com.example.instaclone.Models.User
import com.example.instaclone.R
import com.example.instaclone.Utils.USER_NODE
import com.example.instaclone.databinding.PostRvDesignBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso

class PostAdapter (var context: Context,var postlist:ArrayList<PostModel>):RecyclerView.Adapter<PostAdapter.MyHolder>(){


    inner class MyHolder(var binding:PostRvDesignBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        var binding=PostRvDesignBinding.inflate(LayoutInflater.from(context),parent,false)

        return MyHolder(binding)
    }

    override fun getItemCount(): Int {
        return postlist.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {

        try{
            Firebase.firestore.collection(USER_NODE).document(postlist.get(position).uid!!).get().addOnSuccessListener {

                var user=it.toObject<User>()!!
                Glide.with(context).load(user!!.image).fitCenter().placeholder(R.drawable.profileof).into(holder.binding.userProfileImage)
               // Picasso.get().load(user!!.image).fit()
                   // .centerCrop().into(holder.binding.userProfileImage)
                holder.binding.userProfileName.text=user.name


            }

        }catch (e:java.lang.Exception){

        }

        Glide.with(context).load(postlist.get(position).posturl).fitCenter().placeholder(R.drawable.loading).into(holder.binding.userPostImage)
       // Picasso.get().load(postlist.get(position).posturl).fit()
            //.centerCrop().into(holder.binding.userPostImage)
        holder.binding.postCaption.text=postlist.get(position).caption

    }
}