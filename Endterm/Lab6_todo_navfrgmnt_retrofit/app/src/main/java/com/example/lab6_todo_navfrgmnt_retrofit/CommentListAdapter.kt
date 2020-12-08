package com.example.lab6_todo_navfrgmnt_retrofit

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.lab6_todo_navfrgmnt_retrofit.Model.Comment
import com.example.lab6_todo_navfrgmnt_retrofit.Model.Task
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentListAdapter(
    val comments: MutableList<Comment>,
    val context: Context,
) : RecyclerView.Adapter<CommentListAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.comments_view, parent, false)
        instance = this
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val comment = comments[position]

        holder.nameText.text = comment.name_com
        holder.emailText.text = comment.email_com
        holder.commentText.text = comment.comments

    }

    override fun getItemCount(): Int {
        return comments.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameText: TextView = itemView.findViewById(R.id.comment_user_name)
        var emailText: TextView = itemView.findViewById(R.id.comment_email)
        var commentText: TextView = itemView.findViewById(R.id.comment_text)
    }

    companion object {
        lateinit var instance: CommentListAdapter
    }
}