package com.example.lab6_todo_navfrgmnt_retrofit.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.lab6_todo_navfrgmnt_retrofit.MainActivity
import com.example.lab6_todo_navfrgmnt_retrofit.Model.Comment
import com.example.lab6_todo_navfrgmnt_retrofit.Model.Task
import com.example.lab6_todo_navfrgmnt_retrofit.Model.User
import com.example.lab6_todo_navfrgmnt_retrofit.R
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_todolist.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class   DetailFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val taskId = arguments?.getInt("taskId")!!
        val userId = arguments?.getInt("userId")!!

        MainActivity.apiService.getTaskById(taskId).enqueue(object : Callback<Task> {
            override fun onFailure(call: Call<Task>, t: Throwable) {
                Log.e("error", t.message.toString())
            }
            override fun onResponse(call: Call<Task>, response: Response<Task>) {
                Log.e("Response body: ", response.body()!!.toString())
                val task = response.body()!!
                detail_title.text = task.title
                detail_completed_checkbox.isChecked = task.completed
            }
        })

        MainActivity.apiService.getUser(userId).enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e("error", t.message.toString())
            }
            override fun onResponse(call: Call<User>, response: Response<User>) {
                Log.e("Response body: ", response.body()!!.toString())
                val user = response.body()!!
                detail_user_email.text = user.email
                detail_user_name.text = user.name
                detail_user_phone.text = user.phone
                detail_user_website.text = user.website
            }
        })

        btn_detail_comments.setOnClickListener {

            val bundle = Bundle()
            bundle.putInt("taskId", taskId)

            it.findNavController().navigate(R.id.action_detailFragment_to_commentsFragment,bundle)
        }

    }
}