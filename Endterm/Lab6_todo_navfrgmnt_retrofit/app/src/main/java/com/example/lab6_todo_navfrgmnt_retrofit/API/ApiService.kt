package com.example.lab6_todo_navfrgmnt_retrofit.API

import com.example.lab6_todo_navfrgmnt_retrofit.Model.Comment
import com.example.lab6_todo_navfrgmnt_retrofit.Model.Task
import com.example.lab6_todo_navfrgmnt_retrofit.Model.User
import retrofit2.Call
import retrofit2.http.*

public interface ApiService {
    @GET("todos/")
    fun getTasks(): Call<MutableList<Task>>

    @GET("todos/{id}/")
    fun getTaskById(@Path("id") todoId: Int): Call<Task>

    @FormUrlEncoded
    @POST("todos")
    fun addTask(
        @Field("title") title: String,
        @Field("completed") completed: Boolean
    ): Call<Task>

    @FormUrlEncoded
    @PUT("todos/{id}/")
    fun updateTask(
            @Path("id") taskId: Int,
            @Field("completed") completed: Boolean
    ): Call<Task>

    @GET("users/{id}/")
    fun getUser(@Path("id") userId: Int): Call<User>

    @GET("comments/")
    fun getComments(): Call<MutableList<Comment>>

    @GET("comments/{id}/")
    fun getComment(@Path("id") id: Int): Call<Comment>
}

