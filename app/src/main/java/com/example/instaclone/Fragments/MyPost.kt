package com.example.instaclone.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.instaclone.Models.PostModel
import com.example.instaclone.Models.User
import com.example.instaclone.R
import com.example.instaclone.adapters.MyPostRVAdapter
import com.example.instaclone.databinding.FragmentMyPostBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase


class MyPost : Fragment() {

    private lateinit var binding:FragmentMyPostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentMyPostBinding.inflate(inflater, container, false)
        var postlist=ArrayList<PostModel>()
        var adapter=MyPostRVAdapter(requireContext(),postlist)
        binding.rv.layoutManager=StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)
        binding.rv.adapter=adapter

        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid).get().addOnSuccessListener {
            var templist= arrayListOf<PostModel>()
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