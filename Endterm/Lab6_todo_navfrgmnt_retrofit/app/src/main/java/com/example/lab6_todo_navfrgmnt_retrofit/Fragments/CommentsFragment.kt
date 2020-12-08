package com.example.lab6_todo_navfrgmnt_retrofit.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab6_todo_navfrgmnt_retrofit.CommentListAdapter
import com.example.lab6_todo_navfrgmnt_retrofit.MainActivity
import com.example.lab6_todo_navfrgmnt_retrofit.Model.Comment
import com.example.lab6_todo_navfrgmnt_retrofit.Model.Task
import com.example.lab6_todo_navfrgmnt_retrofit.Model.User
import com.example.lab6_todo_navfrgmnt_retrofit.R
import com.example.lab6_todo_navfrgmnt_retrofit.TaskListAdapter
import kotlinx.android.synthetic.main.comments_view.*
import kotlinx.android.synthetic.main.fragment_comments.*
import kotlinx.android.synthetic.main.fragment_create.*
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_todolist.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CommentsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comments, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        MainActivity.apiService.getComments().enqueue(object : Callback<MutableList<Comment>> {
            override fun onFailure(call: Call<MutableList<Comment>>, t: Throwable) {
                Log.e("error", t.message.toString())
            }
            override fun onResponse(call: Call<MutableList<Comment>>, response: Response<MutableList<Comment>>) {
                Log.e("Response size: ", response.body()!!.size.toString())
                MainActivity.comments = response.body()!!
                (comments_recycle_view.adapter as CommentListAdapter).notifyDataSetChanged()
            }
        })

        comments_recycle_view.adapter = CommentListAdapter(MainActivity.comments,view.context)
        comments_recycle_view.layoutManager = LinearLayoutManager(view.context)

        val commentId = arguments?.getInt("taskId")!!



        MainActivity.apiService.getComment(commentId).enqueue(object : Callback<Comment>{
            override fun onFailure(call: Call<Comment>, t: Throwable) {
                Log.e("error", t.message.toString())
            }
            override fun onResponse(call: Call<Comment>, response: Response<Comment>) {
                Log.e("Response body: ", response.body()!!.toString())
                val task = response.body()!!
                val comment = response.body()!!
                comment_user_name.text = comment.name_com
                comment_email.text = comment.email_com
                comment_text.text = comment.comments

            }
        })
    }
}