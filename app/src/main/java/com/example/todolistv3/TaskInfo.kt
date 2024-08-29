package com.example.todolistv3

import android.widget.Button

data class TaskInfo(val name: String, var tasks:MutableList<String>, var progress:Double, var status:MutableList<Boolean>)
