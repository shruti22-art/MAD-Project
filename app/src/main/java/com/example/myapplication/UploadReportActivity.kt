package com.example.myapplication

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class UploadReportActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_report)

        val etName = findViewById<EditText>(R.id.etPatientName)
        val etProblem = findViewById<EditText>(R.id.etProblem)
        val etTime = findViewById<EditText>(R.id.etTime)
        val btnUpload = findViewById<Button>(R.id.btnUpload)

        val sharedPref = getSharedPreferences("patients", MODE_PRIVATE)

        btnUpload.setOnClickListener {

            val name = etName.text.toString().trim()
            val problem = etProblem.text.toString().trim()
            val time = etTime.text.toString().trim()

            if (name.isEmpty() || problem.isEmpty() || time.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val data = "$name - $problem - $time"

            // 🔥 SAVE LOCALLY
            sharedPref.edit()
                .putString("patient_data", data)
                .apply()

            Toast.makeText(this, "Report Uploaded & Saved Locally", Toast.LENGTH_LONG).show()
        }
    }
}