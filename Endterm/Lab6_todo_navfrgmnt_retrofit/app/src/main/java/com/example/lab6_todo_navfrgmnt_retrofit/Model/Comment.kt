package com.example.lab6_todo_navfrgmnt_retrofit.Model

import com.google.gson.annotations.SerializedName

data class Comment(
    @SerializedName("id")
    val id: Int,

    @SerializedName("postId")
    val postId: Int,
    @SerializedName("name_com")
    val name_com: String,
    @SerializedName("email_com")
    val email_com: String,
    @SerializedName("comments")
    val comments: String
)