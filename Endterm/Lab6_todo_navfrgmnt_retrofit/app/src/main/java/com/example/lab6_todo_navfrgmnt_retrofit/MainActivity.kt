package com.example.lab6_todo_navfrgmnt_retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import com.example.lab6_todo_navfrgmnt_retrofit.API.ApiService
import com.example.lab6_todo_navfrgmnt_retrofit.Model.Comment
import com.example.lab6_todo_navfrgmnt_retrofit.Model.Task
import com.example.lab6_todo_navfrgmnt_retrofit.Model.User
import kotlinx.android.synthetic.main.fragment_todolist.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()

        apiService = retrofit.create(ApiService::class.java)
        tasks = mutableListOf()
        comments = mutableListOf()


    }

    override fun onSupportNavigateUp(): Boolean = findNavController(R.id.nav_host_fragment).navigateUp()

    companion object {
        lateinit var tasks: MutableList<Task>
        lateinit var comments: MutableList<Comment>
        lateinit var instance: MainActivity
        lateinit var apiService: ApiService
        fun createTask(task: Task) {
        }

    }

}

