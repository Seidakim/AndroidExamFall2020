package com.example.lab6_todo_navfrgmnt_retrofit.Model

import com.google.gson.annotations.SerializedName

data class Task(
    @SerializedName("id")
    val id: Int,
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("completed")
    val completed: Boolean,
    @SerializedName("comments")
    val postId: String
)