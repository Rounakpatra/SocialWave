package com.example.instaclone

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.instaclone.Models.User
import com.example.instaclone.Utils.USER_NODE
import com.example.instaclone.Utils.USER_PROFILE_FOLDER
import com.example.instaclone.Utils.uploadimage
import com.example.instaclone.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Signup : AppCompatActivity() {

    val binding by lazy {
        ActivitySignupBinding.inflate(layoutInflater)
    }

    lateinit var myuser:User

    private val launcher=registerForActivityResult(ActivityResultContracts.GetContent()){
        uri->

        uri?.let {
            uploadimage(uri, USER_PROFILE_FOLDER){
                if(it!=null){
                    myuser.image=it
                    binding.profileImage.setImageURI(uri)
                }
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        window.statusBarColor=Color.TRANSPARENT
        supportActionBar?.hide()

        myuser= User()

        binding.signupbtn.setOnClickListener {
            var typedname=binding.typedname.editText?.text.toString()
            var typedemail=binding.typedemail.editText?.text.toString()
            var typedpassword=binding.typedpassword.editText?.text.toString()

            if(typedname.isBlank()||typedemail.isBlank()||typedpassword.isBlank()){
                Toast.makeText(this@Signup,"Fill all the fields",Toast.LENGTH_SHORT).show()
            }else{
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(typedemail,typedpassword).addOnCompleteListener {
                    result->
                    if(result.isSuccessful){
                        myuser.name=typedname
                        myuser.password=typedpassword
                        myuser.email=typedemail

                        Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid).set(myuser).addOnSuccessListener {
                            startActivity(Intent(this@Signup,HomeActivity::class.java))
                            finish()
                        }

                    }else{
                        Toast.makeText(this@Signup,"Error Signing in",Toast.LENGTH_SHORT).show()
                    }
                }
            }


        }

        binding.addprofiletxt.setOnClickListener {
            launcher.launch("image/*")
        }

        binding.logintxt.setOnClickListener {
            startActivity(Intent(this@Signup,Login::class.java))
            finish()
        }

    }
}