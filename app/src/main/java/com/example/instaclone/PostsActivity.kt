package com.example.instaclone

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.instaclone.Models.PostModel
import com.example.instaclone.Models.User
import com.example.instaclone.Utils.*
import com.example.instaclone.databinding.ActivityPostsBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase

class PostsActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityPostsBinding.inflate(layoutInflater)
    }

    var imageurl: String? = null

    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->

        uri?.let {
            uploadimage(uri, POST_FOLDER) { url ->
                if (it != null) {
                    binding.postImage.setImageURI(uri)
                    imageurl = url
                }
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        window.statusBarColor = Color.TRANSPARENT
        supportActionBar?.hide()

        binding.cancelpostBtn.setOnClickListener {
            startActivity(Intent(this@PostsActivity,HomeActivity::class.java))
            finish()
        }

        binding.postImage.setOnClickListener {
            launcher.launch("image/*")
        }

        binding.postBtn.setOnClickListener {



            Firebase.firestore.collection(USER_NODE).document().get().addOnSuccessListener {


                var typedcaption = binding.typedcaption.editText?.text.toString()
                val postmodel: PostModel = PostModel(posturl = imageurl!!, caption = typedcaption, uid = Firebase.auth.currentUser!!.uid)
                Firebase.firestore.collection(POST_NODE).document().set(postmodel)
                    .addOnSuccessListener {
                        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid).document()
                            .set(postmodel).addOnSuccessListener {
                                startActivity(Intent(this@PostsActivity,HomeActivity::class.java))
                                finish()
                            }
                    }


            }



        }


    }
}