package com.example.instaclone

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.statusBarColor=Color.TRANSPARENT
        supportActionBar?.hide()

        Handler(Looper.getMainLooper()).postDelayed({
            if(FirebaseAuth.getInstance().currentUser!=null){
                var myintent=Intent(this@MainActivity,HomeActivity::class.java)
                startActivity(myintent)
                finish()
            }else{
                var myintent=Intent(this@MainActivity,Signup::class.java)
                startActivity(myintent)
                finish()
            }

        },3000)
    }
}