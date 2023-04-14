package com.example.app2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app2.Connection.api
import com.example.app2.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var date: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        api.getDelayed().push(object: OnGetData<List<ModelTask>>{
            override fun onGet(data: List<ModelTask>) {
                binding.expiredRec.adapter = TasksAdapter(data)
                binding.expiredRec.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            }

            override fun onError(error: String) {
                Log.e("ERRROR", error)
            }
        }, this)
        binding.next.setOnClickListener {
            refreshRecycler("next")
        }
        binding.prev.setOnClickListener {
            refreshRecycler("prev")
        }
    }
    private fun refreshRecycler(direction: String? = null) {
        api.getPlan(date, direction).push(object: OnGetData<List<ModelTask>>{
            override fun onGet(data: List<ModelTask>) {
                val dateStr = data[0].datetime.substringBefore(" ")
                date  = dateStr
                binding.date.text = dateStr
                setTimeStamp(dateStr)
                binding.date.text = dateStr
                binding.mainCourseRec.adapter = TasksAdapter(data)
                binding.mainCourseRec.adapter!!.notifyDataSetChanged()
                binding.noPlans.visibility = View.VISIBLE
            }

            override fun onError(error: String) {
                Log.e("ERRROR", error)
                binding.noPlans.visibility = View.GONE
            }
        }, this)
    }
    private fun setTimeStamp(dateStr: String) {
        val date: Date = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).parse(dateStr)!!
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        val today: Date = calendar.time
        val diffUnixTimeMillis = date.time - today.time
        val diffHours = (diffUnixTimeMillis+1) / 3600000.0
        val diffDays = (diffHours/24).toInt()
        binding.dayType.visibility = View.VISIBLE
        when (diffDays) {
            0 -> binding.dayType.text = "Сегодня"
            1 -> binding.dayType.text = "Завтра"
            else -> binding.dayType.visibility = View.GONE
        }
    }
}