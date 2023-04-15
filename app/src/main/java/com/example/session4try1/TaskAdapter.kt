package com.example.session4try1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.session4try1.databinding.CourseItemBinding
import java.lang.Math.abs
import java.text.SimpleDateFormat
import java.util.*

class TaskViewHolder(var binding: CourseItemBinding) : RecyclerView.ViewHolder(binding.root)
class TaskAdapter (val list: List<ModelTask>, val onClickTask: OnClickTask) : RecyclerView.Adapter<TaskViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(CourseItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.binding.taskTitle.text = list[position].title
        holder.binding.body.setOnClickListener {
            onClickTask.onClick(list[position])
        }
        val dateLesson: Date = SimpleDateFormat("dd.MM.yyyy hh:mm", Locale.getDefault()).parse(list[position].datetime)
        val today = Date()
        val difference = today.time - dateLesson.time
        val differenceDays = abs(((difference+1)/3600000/24).toInt())
        holder.binding.delay.text = "просрочено - ${differenceDays} ${getDaysText(differenceDays)}"

    }
    private fun getDaysText(days: Int): String {
        if (days%10 == 1 && days!=11) {
            return "день"
        }
        if (days%10 in listOf(2, 3, 4)) {
            return "дня"
        }else {
            return "дней"
        }
    }

}
interface OnClickTask {
    fun onClick(task: ModelTask)
}