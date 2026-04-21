package com.example.myapplication

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class DoctorTimingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_timing)

        val etTiming = findViewById<EditText>(R.id.etTiming)
        val btnSave = findViewById<Button>(R.id.btnSave)

        val docName = intent.getStringExtra("doctorName")
        val docSpec = intent.getStringExtra("specialization")

        btnSave.setOnClickListener {

            val timing = etTiming.text.toString().trim()

            if (timing.isEmpty()) {
                Toast.makeText(this, "Please enter timing", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 🔥 LOCAL STORAGE (FINAL BOOKING)
            val sharedPref = getSharedPreferences("appointments", MODE_PRIVATE)
            val editor = sharedPref.edit()

            editor.putString("doctorName", docName)
            editor.putString("specialization", docSpec)
            editor.putString("timing", timing)
            editor.apply()

            Toast.makeText(
                this,
                "Appointment Booked Successfully",
                Toast.LENGTH_LONG
            ).show()

            finish()
        }
    }
}