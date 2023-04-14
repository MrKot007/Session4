package com.example.app2

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app2.databinding.TaskItemBinding

class TaskViewHolder(var binding: TaskItemBinding) : RecyclerView.ViewHolder(binding.root)
class TasksAdapter(val list: List<ModelTask>) : RecyclerView.Adapter<TaskViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        
    }

}