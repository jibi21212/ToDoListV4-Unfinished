package com.example.todolistv3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView

class taskAdapter (private var taskList: MutableList<TaskInfo>, private val tM:taskStepManager): RecyclerView.Adapter<taskAdapter.taskViewHolder>()
{ //Class in charge of "adapting" all tasks so we can display them to the user
    class taskViewHolder(V: View): RecyclerView.ViewHolder(V){ //Extracts views into variables
        val tvTask: TextView = V.findViewById(R.id.taskName)
        val btnDelete : Button = V.findViewById(R.id.delete_task)
        val prog : com.google.android.material.progressindicator.CircularProgressIndicator = V.findViewById(R.id.taskProgress)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): taskViewHolder { //Does the following upon craetion of viewHolder
        val view = LayoutInflater.from(parent.context).inflate(R.layout.taskbox,parent,false)
        //Chooses the layout to inflate the recycler view with (taskbox.xml in this case)
        return taskViewHolder(view) //Call taskView with the layout selected, extract variables from the view passed to taskViewHolder
    }

    override fun onBindViewHolder(holder: taskViewHolder, position: Int) { //Binds all this code and data to each position of the recycler view
        val t = taskList[position]
        holder.tvTask.text = t.name


        holder.btnDelete.setOnClickListener{ //If delete button pressed it removes it from the recycler view
            taskList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, taskList.size)
        }
        holder.tvTask.setOnClickListener{
            tM.openTask(t) //Opens the tasks
        }
    }


    override fun getItemCount(): Int = taskList.size //Returns size of taskList

    fun updateList(newList:MutableList<TaskInfo>){ //Updates list
        this.taskList=newList
        notifyDataSetChanged()
    }

}