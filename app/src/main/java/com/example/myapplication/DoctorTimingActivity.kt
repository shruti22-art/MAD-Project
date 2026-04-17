package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DoctorTimingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // IMPORTANT: this must match your XML file name
        setContentView(R.layout.activity_doctor_timing)

        // Connect XML views
        val etDoctorName = findViewById<EditText>(R.id.etDoctorName)
        val etTiming = findViewById<EditText>(R.id.etTiming)
        val btnSave = findViewById<Button>(R.id.btnSave)

        // Button click logic
        btnSave.setOnClickListener {

            val name = etDoctorName.text.toString().trim()
            val timing = etTiming.text.toString().trim()

            if (name.isEmpty() || timing.isEmpty()) {
                Toast.makeText(
                    this,
                    "Please enter doctor name and timing",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    this,
                    "Saved: $name - $timing",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
