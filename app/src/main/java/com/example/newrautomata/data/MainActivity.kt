package com.example.newrautomata.data

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newrautomata.R
import com.example.newrautomata.data.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_ID="extra_id"
    }
    private val apiService = ApiService.getService()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = mutableListOf<User>()
        val adapter = RecyclerAdapter(list){
            val intent = Intent( this, UserActivity::class.java )
            intent.putExtra(EXTRA_ID,it)
            startActivity(intent)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.adapter = adapter

        lifecycleScope.launch(Dispatchers.IO){
            val users = apiService.getUsers()
            list.addAll(users)
            withContext(Dispatchers.Main){
                adapter.notifyDataSetChanged()
            }
        }
    }
}