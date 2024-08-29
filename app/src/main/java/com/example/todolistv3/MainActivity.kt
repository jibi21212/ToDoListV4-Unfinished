package com.example.todolistv3

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistv3.R
import com.example.todolistv3.displayTaskAdapter
import com.example.todolistv3.taskAdapter
import com.example.todolistv3.taskManager
import com.example.todolistv3.taskStepManager

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var taskadapter: taskAdapter
    private lateinit var taskmanager: taskManager
    private lateinit var add_task_button: Button
    private lateinit var etTaskName: EditText
    private lateinit var sideBar: LinearLayout
    private lateinit var taskDisplay: LinearLayout
    private lateinit var clr2: Button
    private lateinit var etTaskname2: EditText
    private lateinit var addButton2: Button
    private lateinit var taskStepManager: taskStepManager
    private lateinit var displayTaskAdapter: displayTaskAdapter
    private lateinit var closeTask: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        taskDisplay = findViewById(R.id.taskdisplay)
        val rV2 = findViewById<RecyclerView>(R.id.rV)
        rV2.layoutManager = LinearLayoutManager(this)
        displayTaskAdapter = displayTaskAdapter(mutableListOf()) //Initialize recyclerview2
        sideBar = findViewById<LinearLayout>(R.id.sideBar)
        taskStepManager = taskStepManager(displayTaskAdapter, sideBar, taskDisplay)
        rV2.adapter = taskStepManager.adapter


        taskadapter = taskAdapter(mutableListOf(),taskStepManager)
        val recyclerView = findViewById<RecyclerView>(R.id.rVM) //Initialize recyclerview
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = taskadapter
        taskmanager = taskManager(taskadapter)

        sideBar.visibility = View.VISIBLE
        taskDisplay.visibility = View.GONE //Set to sideBar
        add_task_button = findViewById(R.id.addTask) //Connect variables to views
        etTaskName = findViewById(R.id.editTaskSB)
        clr2 = findViewById(R.id.clear2)
        etTaskname2 = findViewById(R.id.editText2)
        addButton2 = findViewById(R.id.addStep)
        closeTask = findViewById(R.id.CT)

        add_task_button.setOnClickListener(this) //Set on click listeners
        addButton2.setOnClickListener(this)
        clr2.setOnClickListener(this)
        closeTask.setOnClickListener(this)
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.addTask ->{
                if(etTaskName.text.toString().isNotEmpty())
                {
                    taskmanager.addTask(etTaskName.text.toString(), mutableListOf<String>(),0.0)
                    etTaskName.text.clear()
                }
            }
            R.id.addStep -> {
                if(etTaskname2.text.toString().isNotEmpty())
                {
                    taskStepManager.addStep(etTaskname2.text.toString())
                }
                etTaskname2.text.clear()
            }
            R.id.clear2 -> {
                taskStepManager.clearHistory()
                etTaskname2.text.clear()
                displayTaskAdapter.currTask.tasks.clear()
            }
            R.id.CT -> {
                taskStepManager.closeTask()
            }
        }
    }



}