package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class StudentListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)

        recyclerView = findViewById(R.id.recyclerViewStudents)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val studentList = listOf(
            "Rahul - B.Tech",
            "Aman - MBBS",
            "Riya - Nursing"
        )

        val adapter = SimpleAdapter(studentList)
        recyclerView.adapter = adapter

        adapter.setOnItemClickListener(object : SimpleAdapter.OnItemClickListener {
            override fun onClick(item: String) {
                Toast.makeText(this@StudentListActivity, item, Toast.LENGTH_SHORT).show()
            }
        })
    }
}