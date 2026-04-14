package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DoctorDashboardActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var btnPrescription: Button
    private lateinit var btnHistory: Button
    private lateinit var btnUpload: Button
    private lateinit var btnChat: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_dashboard)

        // 🔹 Initialize views
        recyclerView = findViewById(R.id.appointmentsRecyclerView)
        btnPrescription = findViewById(R.id.btnAddPrescription)
        btnHistory = findViewById(R.id.btnViewHistory)
        btnUpload = findViewById(R.id.btnUploadReports)
        btnChat = findViewById(R.id.btnChat)

        // 🔹 RecyclerView setup
        recyclerView.layoutManager = LinearLayoutManager(this)

        // 🔹 Dummy appointment data
        val appointments = listOf(
            "Riya - Fever - 10:00 AM",
            "Aman - Cold - 11:30 AM",
            "Rahul - Checkup - 1:00 PM"
        )

        val adapter = SimpleAdapter(appointments)
        recyclerView.adapter = adapter

        // ✅ 🔥 CLICK → OPEN PATIENT DETAILS SCREEN
        adapter.setOnItemClickListener { item ->

            val intent = Intent(this, PatientDetailsActivity::class.java)

            // data send कर रहे हैं
            intent.putExtra("patient_data", item)

            startActivity(intent)
        }

        // 🔹 Button Clicks
        btnPrescription.setOnClickListener {
            startActivity(Intent(this, AddPrescriptionActivity::class.java))
        }

        btnHistory.setOnClickListener {
            startActivity(Intent(this, PatientHistoryActivity::class.java))
        }

        btnUpload.setOnClickListener {
            startActivity(Intent(this, UploadReportActivity::class.java))
        }

        btnChat.setOnClickListener {
            startActivity(Intent(this, ChatActivity::class.java))
        }

        // 🔔 Show notification (demo)
        showNotification()
    }

    // 🔔 Simple notification (Toast)
    private fun showNotification() {
        Toast.makeText(this, "New Booking Arrived!", Toast.LENGTH_LONG).show()
    }
}