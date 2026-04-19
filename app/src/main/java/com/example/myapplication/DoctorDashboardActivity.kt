package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DoctorDashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_dashboard)

        Toast.makeText(this, "Doctor Dashboard Opened", Toast.LENGTH_LONG).show()

        val btnPrescription = findViewById<Button>(R.id.btnAddPrescription)
        val btnHistory = findViewById<Button>(R.id.btnViewHistory)
        val btnUpload = findViewById<Button>(R.id.btnUploadReports)
        val btnChat = findViewById<Button>(R.id.btnChat)

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
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        btnLogout.setOnClickListener {

            // Clear saved data (logout)
            val pref = getSharedPreferences("USER_DATA", MODE_PRIVATE)
            pref.edit().clear().apply()

            Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT).show()

            // Go to Login screen
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}