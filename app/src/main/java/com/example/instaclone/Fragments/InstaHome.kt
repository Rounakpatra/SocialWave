package com.example.instaclone.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.instaclone.Models.PostModel
import com.example.instaclone.Models.User
import com.example.instaclone.R
import com.example.instaclone.Utils.POST_NODE
import com.example.instaclone.Utils.USER_NODE
import com.example.instaclone.adapters.PostAdapter
import com.example.instaclone.databinding.FragmentInstaHomeBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase


class InstaHome : Fragment() {

    private lateinit var binding:FragmentInstaHomeBinding
    private var postlist=ArrayList<PostModel>()
    private lateinit var adapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding=FragmentInstaHomeBinding.inflate(inflater, container, false)
        adapter= PostAdapter(requireContext(),postlist)
        binding.rvHome.layoutManager=LinearLayoutManager(requireContext())
        binding.rvHome.adapter=adapter






        Firebase.firestore.collection(POST_NODE).get().addOnSuccessListener {
            var templist=ArrayList<PostModel>()
            postlist.clear()
            for(i in it.documents){
                var post:PostModel=i.toObject<PostModel>()!!
                templist.add(post)
            }
            postlist.addAll(templist)
            adapter.notifyDataSetChanged()
        }



        return binding.root
    }

    companion object {

    }


}