package com.example.todolistv3

import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager

class taskManager(private val adapter: taskAdapter) { //Class in charge of managing all tasks
    val allTasks = mutableListOf<TaskInfo>() // In charge of storing ALL tasks
    fun addTask(name: String, tasks:MutableList<String>, progress:Double) // Adds a task
    {
        val task = TaskInfo(name,tasks,progress, mutableListOf())
        allTasks.add(task)
        adapter.updateList(allTasks)
        adapter.notifyItemInserted(allTasks.size - 1)
    }

}