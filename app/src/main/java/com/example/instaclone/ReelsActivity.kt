package com.example.instaclone

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ReelsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reels2)

        window.statusBarColor= Color.TRANSPARENT
        supportActionBar?.hide()
    }
}