package com.example.todolistv3

import android.util.Log
import android.view.View
import android.widget.LinearLayout
class taskStepManager (val adapter: displayTaskAdapter, layout1:LinearLayout, layout2: LinearLayout){
    val l1 = layout1
    val l2 = layout2
    var steps:MutableMap<String,Boolean> = mutableMapOf()

    fun openTask(clicked: TaskInfo){ //Switches to second recycler view and displays the current task
        l1.visibility = View.GONE
        l2.visibility = View.VISIBLE
        adapter.setTask(clicked)
        updateSteps(clicked.tasks, clicked.status)
    }
    fun addStep(name:String)
    {
        adapter.currTask.tasks.add(name)
        adapter.currTask.status.add(false)
        updateSteps(adapter.currTask.tasks, adapter.currTask.status)
    }

    fun closeTask()
    { //Closes the task and goes back to sideBar
        l2.visibility = View.GONE
        l1.visibility = View.VISIBLE
        clearHistory()
    }
    fun clearHistory()
    { //Clears history
        steps.clear()
        adapter.updateList(steps)
    }
    fun updateSteps(newSteps:MutableList<String>, newStatus:MutableList<Boolean>){
        steps.clear()
        steps = newSteps.zip(newStatus).toMap().toMutableMap()
        adapter.updateList(steps)
        adapter.notifyDataSetChanged()

    }
}