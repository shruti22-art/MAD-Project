package com.example.myapplication

import android.annotation.SuppressLint
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

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_dashboard)

        recyclerView = findViewById(R.id.appointmentsRecyclerView)
        btnPrescription = findViewById(R.id.btnAddPrescription)
        btnHistory = findViewById(R.id.btnViewHistory)
        btnUpload = findViewById(R.id.btnUploadReports)
        btnChat = findViewById(R.id.btnChat)

        recyclerView.layoutManager = LinearLayoutManager(this)

        val appointments = listOf(
            "Riya - Fever - 10:00 AM",
            "Aman - Cold - 11:30 AM",
            "Rahul - Checkup - 1:00 PM"
        )

        val adapter = SimpleAdapter(appointments)
        recyclerView.adapter = adapter

        // ✅ CORRECT CLICK LISTENER
        adapter.setOnItemClickListener(object : SimpleAdapter.OnItemClickListener {
            override fun onClick(item: String) {

                val intent = Intent(this@DoctorDashboardActivity, PatientDetailsActivity::class.java)
                intent.putExtra("patient_data", item)
                startActivity(intent)
            }
        })

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

        showNotification()
    }

    private fun showNotification() {
        Toast.makeText(this, "New Booking Arrived!", Toast.LENGTH_LONG).show()
    }
}