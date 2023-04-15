package com.example.session4try1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.session4try1.databinding.MainCourseItemBinding

class MainTaskViewHolder(var binding: MainCourseItemBinding) : RecyclerView.ViewHolder(binding.root)
class MainTaskAdapter(val list: List<ModelTask>) : RecyclerView.Adapter<MainTaskViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainTaskViewHolder {
        return MainTaskViewHolder(MainCourseItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MainTaskViewHolder, position: Int) {
        holder.binding.mainTaskTitle.text = list[position].title
        val durationHours = list[position].duration/60
        holder.binding.textView6.text = "$durationHours ${getHoursText(durationHours)}"
        if (list[position].isComplete) {
            holder.binding.status.text = "выполнено"
        }else {
            holder.binding.statusBG.setCardBackgroundColor(holder.binding.root.context.getColor(R.color.begin))
            holder.binding.status.text = "начать"
        }
    }
    private fun getHoursText(hours: Int): String {
        if (hours%10 == 1 && hours!=11) {
            return "час"
        }else if (hours%10 in listOf(2, 3, 4)) {
            return "часа"
        }else {
            return "часов"
        }

    }

}