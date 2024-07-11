package com.example.instaclone.Models

class PostModel {

    var posturl:String?=null
    var caption:String?=null
    var uid:String?=null

    constructor()

    constructor(posturl: String?, caption: String?) {
        this.posturl = posturl
        this.caption = caption
    }

    constructor(posturl: String?, caption: String?, uid: String?) {
        this.posturl = posturl
        this.caption = caption
        this.uid = uid
    }


}