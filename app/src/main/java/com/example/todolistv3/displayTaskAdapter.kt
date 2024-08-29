package com.example.todolistv3

import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class displayTaskAdapter (private var tasks: MutableList<String>): RecyclerView.Adapter<displayTaskAdapter.taskViewHolder>()
{
    lateinit var currTask:TaskInfo //Current task at hand
    var done = 0.0
    var toDo = 0.0
    var totalProg = 0.0
    class taskViewHolder(V: View): RecyclerView.ViewHolder(V) //create an xml file with a textview + delete button
    {
        val steps: TextView = V.findViewById(R.id.taskSteps)
        val delete: Button = V.findViewById(R.id.delete_task)
        val checkBox: CheckBox = V.findViewById(R.id.check)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): taskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.steps,parent,false)
        return taskViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: taskViewHolder, position: Int) {
        val t = tasks[position]
        holder.steps.text = t
        holder.checkBox.isChecked = currTask.status[position]
        holder.checkBox.setOnCheckedChangeListener{ buttonView, isChecked ->
            currTask.status[position] = isChecked
            currTask.progress = getProgress()
        }
        holder.delete.setOnClickListener{
            tasks.removeAt(position)
            currTask.tasks.removeAt(position)
            notifyItemRemoved(position) //Sets up delete task button
            notifyItemRangeChanged(position,tasks.size)
            currTask.progress = getProgress()
            Log.d("TaskSteps", "Binding step: $t at position: $position")
        }
    }
    fun getProgress():Double
    {
        done = currTask.status.count{it}.toDouble()
        if(toDo!=0.0)
        {
            toDo = currTask.tasks.size.toDouble()
            totalProg = (done/toDo) * 100
            return totalProg
        }
        return 0.0
    }
    fun updateList(newList: MutableMap<String,Boolean>)
    {
        tasks.clear()
        tasks.addAll(newList.keys)
        currTask.progress = getProgress()
        notifyItemInserted(tasks.size - 1)
    }
    fun setTask(t:TaskInfo){
        currTask = t
    }
}