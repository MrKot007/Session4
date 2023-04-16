package com.example.session4try1

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.session4try1.Connection.api
import com.example.session4try1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        api.getExpired().push(object: OnGetData<List<ModelTask>>{
            override fun onGet(data: List<ModelTask>) {
                binding.expiredRecycler.adapter = ExpiredAdapter(data)
                binding.expiredRecycler.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            }

            override fun onError(error: String) {
                showAlertdialog(error, this@MainActivity)
            }
        }, this)
    }
    companion object {
        fun showAlertdialog(reason: String, context: Context) {
            AlertDialog.Builder(context)
                .setTitle(reason)
                .setPositiveButton("Попробовать снова") {
                        dialog, id -> dialog.cancel()
                }.create().show()
        }
    }

}