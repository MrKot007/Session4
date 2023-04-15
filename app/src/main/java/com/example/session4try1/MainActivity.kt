package com.example.session4try1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.session4try1.Connection.api
import com.example.session4try1.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var date: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        refreshRecycler()

        api.getExpired().push(object: OnGetData<List<ModelTask>> {
            override fun onGet(data: List<ModelTask>) {
                binding.expiredLessons.adapter = TaskAdapter(data, object: OnClickTask{
                    override fun onClick(task: ModelTask) {
                        TODO("Not yet implemented")
                    }

                })
                binding.expiredLessons.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            }

            override fun onError(error: String) {
                Log.e("ERRROR", error)
            }
        }, this)
    }
    private fun refreshRecycler(direction: String? = null) {
        api.getPlan(date, direction).push(object: OnGetData<List<ModelTask>> {
            override fun onGet(data: List<ModelTask>) {
                date = data[0].datetime.substringBefore(" ")
                val dateStr = date
                binding.date.text = dateStr
                binding.manRecycler.adapter = MainTaskAdapter(data)
                binding.manRecycler.layoutManager = LinearLayoutManager(this@MainActivity)
            }

            override fun onError(error: String) {
                Log.e("ERRROR", error)
            }

        }, this)
    }
    private fun getTimeStamp(dateStr: String) {
        val date: Date = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).parse(dateStr)!!
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        val today: Date = calendar.time
        val differenceMls = date.time - today.time
        val differenceHrs = (differenceMls+1)/3600000
    }
}