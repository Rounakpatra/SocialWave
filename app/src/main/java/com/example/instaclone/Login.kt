package com.example.instaclone

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.instaclone.Models.User
import com.example.instaclone.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        window.statusBarColor = Color.TRANSPARENT
        supportActionBar?.hide()

        binding.signbtn.setOnClickListener {
            startActivity(Intent(this@Login, Signup::class.java))
            finish()
        }

        binding.loginbtn.setOnClickListener {
            var typedemail = binding.typedemail.editText?.text.toString()
            var typedpassword = binding.typedpassword.editText?.text.toString()

            if (typedemail.isBlank() || typedpassword.isBlank()) {
                Toast.makeText(this@Login, "Fill all the fields", Toast.LENGTH_SHORT).show()
            } else {

                var myuser=User(typedemail,typedpassword)

                FirebaseAuth.getInstance().signInWithEmailAndPassword(typedemail!!, typedpassword!!)
                    .addOnCompleteListener {

                        if(it.isSuccessful){
                            startActivity(Intent(this@Login,HomeActivity::class.java))
                            finish()
                        }else{
                            Toast.makeText(this@Login, "Error Logging In", Toast.LENGTH_SHORT).show()
                        }


                    }
            }


        }
    }
}