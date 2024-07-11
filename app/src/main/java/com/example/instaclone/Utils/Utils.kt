package com.example.instaclone.Utils

import android.net.Uri
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID


fun uploadimage(uri: Uri, foldername: String,callback:(String?)->Unit) {
    var imageurl:String?=null
    FirebaseStorage.getInstance().getReference(foldername).child(UUID.randomUUID().toString())
        .putFile(uri).addOnSuccessListener {
            it.storage.downloadUrl.addOnSuccessListener {
                imageurl=it.toString()
                callback(imageurl)
            }
        }

}