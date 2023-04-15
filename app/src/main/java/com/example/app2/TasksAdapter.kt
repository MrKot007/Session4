package com.example.app2

import android.app.ActionBar.LayoutParams
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.marginTop
import androidx.core.view.setMargins
import androidx.recyclerview.widget.RecyclerView
import com.example.app2.databinding.TaskItemBinding
import java.lang.Math.abs
import java.text.SimpleDateFormat
import java.util.*
//ViewHolder for task
class TaskViewHolder(var binding: TaskItemBinding) : RecyclerView.ViewHolder(binding.root)
//
class TasksAdapter(val list: List<ModelTask>, val onClickTask: OnClickTask) : RecyclerView.Adapter<TaskViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(TaskItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.binding.name.text = list[position].title
        val hour = list[position].duration / 60
        holder.binding.duration.text = "$hour ${getHourText(hour)}"
        holder.binding.body.setOnClickListener {
            onClickTask.onClick(list[position])
        }
        if (list[position].isComplete) {
            holder.binding.status.text = "выполнено"
            val params = LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
            )
            params.setMargins(160, 12, 12, 12)
            holder.binding.constr.layoutParams = params
            //holder.binding.statusBgc.setCardBackgroundColor(holder.binding.root.context.getColor(R.color.task_complete))
        }else {
            val dateLesson: Date = SimpleDateFormat("dd.MM.yyyy hh:mm", Locale.getDefault()).parse(list[position].datetime)
            val today = Date()
            if (today.time < dateLesson.time) {
                val params = LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT
                )
                params.setMargins(160, 12, 12, 12)
                holder.binding.constr.layoutParams = params
                holder.binding.status.text = "начать"
                holder.binding.statusBgc.setCardBackgroundColor(holder.binding.root.context.getColor(R.color.task_not_complete))
            }else {
                val delayTime = abs(((dateLesson.time - today.time)/3600000/24).toInt())
                holder.binding.status.text = "просрочено - ${delayTime} ${getHourText(delayTime)}"
                holder.binding.duration.visibility = View.GONE
                //holder.binding.statusBgc.setCardBackgroundColor(holder.binding.root.context.getColor(R.color.task_not_complete))
            }
        }
    }

    private fun getHourText(hour: Int): String {
        if (hour%10 == 1 && hour != 11) {
            return "час"
        }
        if (hour%10 in listOf(2, 3, 4)) {
            return "часа"
        }
        return "часов"
    }

}
interface OnClickTask {
    fun onClick(task: ModelTask)
}