package com.example.myapplication

import android.content.SharedPreferences
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

        val sharedPref = getSharedPreferences("patients", MODE_PRIVATE)

        // 🔥 STEP 1: get from intent OR fallback to saved data
        var data = intent.getStringExtra("patient_data")

        if (data != null) {
            // 🔥 SAVE LOCALLY
            val editor = sharedPref.edit()
            editor.putString("patient_data", data)
            editor.apply()
        } else {
            // 🔥 LOAD FROM LOCAL STORAGE
            data = sharedPref.getString("patient_data", null)
        }

        // 🔥 DISPLAY DATA
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