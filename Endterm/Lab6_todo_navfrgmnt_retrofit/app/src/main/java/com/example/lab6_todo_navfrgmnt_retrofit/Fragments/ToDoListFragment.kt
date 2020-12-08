package com.example.lab6_todo_navfrgmnt_retrofit.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab6_todo_navfrgmnt_retrofit.MainActivity
import com.example.lab6_todo_navfrgmnt_retrofit.Model.Task
import com.example.lab6_todo_navfrgmnt_retrofit.R
import com.example.lab6_todo_navfrgmnt_retrofit.TaskListAdapter
import kotlinx.android.synthetic.main.fragment_todolist.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ToDoListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_todolist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        tasks_recycle_view.adapter = TaskListAdapter(view.context)
        tasks_recycle_view.layoutManager = LinearLayoutManager(view.context)

        MainActivity.apiService.getTasks().enqueue(object : Callback<MutableList<Task>> {
            override fun onFailure(call: Call<MutableList<Task>>, t: Throwable) {
                Log.e("error", t.message.toString())
            }
            override fun onResponse(call: Call<MutableList<Task>>, response: Response<MutableList<Task>>) {
                Log.e("Response size: ", response.body()!!.size.toString())
                MainActivity.tasks = response.body()!!
                (tasks_recycle_view.adapter as TaskListAdapter).notifyDataSetChanged()
            }
        })

        start_creating_button.setOnClickListener {
            val action = ToDoListFragmentDirections.actionToDoListFragmentToCreateFragment()
            it.findNavController().navigate(action)
        }
    }
}