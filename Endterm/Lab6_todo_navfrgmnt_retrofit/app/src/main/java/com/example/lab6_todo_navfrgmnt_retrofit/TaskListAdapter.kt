package com.example.lab6_todo_navfrgmnt_retrofit

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.lab6_todo_navfrgmnt_retrofit.Fragments.ToDoListFragment
import com.example.lab6_todo_navfrgmnt_retrofit.Fragments.ToDoListFragmentDirections
import com.example.lab6_todo_navfrgmnt_retrofit.Model.Task
import kotlinx.android.synthetic.main.fragment_create.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TaskListAdapter(
    val context: Context,
) : RecyclerView.Adapter<TaskListAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.singletask_view, parent, false)
        instance = this
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val task = MainActivity.tasks[position]
        holder.titleText.text = task.title
        holder.completedCheckBox.isChecked = task.completed


        holder.itemView.setOnClickListener {
            val bundle = bundleOf("taskId" to task.id, "userId" to task.userId)
            it.findNavController().navigate(R.id.action_toDoListFragment_to_detailFragment, bundle)
        }

        val checkBox = holder.itemView.findViewById<CheckBox>(R.id.single_completed_checkbox)
        checkBox.setOnClickListener {
            MainActivity.apiService.updateTask(
                task.id,
                checkBox.isChecked
            ).enqueue(object : Callback<Task> {
                override fun onFailure(call: Call<Task>, t: Throwable) {
                    Log.e("error", t.message.toString())
                }

                override fun onResponse(call: Call<Task>, response: Response<Task>) {
                    Log.e("Response body: ", response.body()!!.toString())
                }
            })
        }
    }

    override fun getItemCount(): Int {
        return MainActivity.tasks.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titleText: TextView = itemView.findViewById(R.id.single_title)
        var completedCheckBox: CheckBox = itemView.findViewById(R.id.single_completed_checkbox)
    }

    companion object {
        lateinit var instance: TaskListAdapter
    }
}