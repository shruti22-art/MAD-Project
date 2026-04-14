package com.example.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PatientDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_details)

        val tvName = findViewById<TextView>(R.id.tvPatientName)
        val tvProblem = findViewById<TextView>(R.id.tvProblem)
        val tvTime = findViewById<TextView>(R.id.tvTime)

        // Get data from intent
        val data = intent.getStringExtra("patient_data")

        if (data != null) {
            val parts = data.split(" - ")

            if (parts.size >= 3) {
                tvName.text = "Name: ${parts[0]}"
                tvProblem.text = "Problem: ${parts[1]}"
                tvTime.text = "Time: ${parts[2]}"
            }
        }
    }
}