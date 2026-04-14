package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PatientHistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_history)

        val recyclerView = findViewById<RecyclerView>(R.id.historyRecyclerView)

        val historyList = listOf(
            "Riya - Fever - 10 Jan",
            "Aman - Cold - 12 Jan",
            "Rahul - Checkup - 15 Jan"
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = SimpleAdapter(historyList)
    }
}