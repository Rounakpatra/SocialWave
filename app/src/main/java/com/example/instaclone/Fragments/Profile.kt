package com.example.instaclone.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.instaclone.Models.User
import com.example.instaclone.R
import com.example.instaclone.Utils.USER_NODE
import com.example.instaclone.adapters.ViewPagerAdapter
import com.example.instaclone.databinding.FragmentProfileBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso


class Profile : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        viewPagerAdapter= ViewPagerAdapter(requireActivity().supportFragmentManager)
        viewPagerAdapter.addfragment(MyPost(),"MY POST")
        viewPagerAdapter.addfragment(MyReels(),"MY REELS")
        binding.viewPager.adapter=viewPagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)

        return binding.root
    }

    companion object {

    }

    override fun onStart() {
        super.onStart()

        Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid).get()
            .addOnSuccessListener {
                val myuser:User=it.toObject<User>()!!
                binding.username.text=myuser.name
                binding.useremail.text=myuser.email
                if(!myuser.image.isNullOrEmpty()){
                    Picasso.get().load(myuser.image).into(binding.userimage)
                }
            }

    }
}