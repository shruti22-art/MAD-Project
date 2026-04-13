package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class AdminDashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_dashboard)

        findViewById<Button>(R.id.btnStudent).setOnClickListener {
            startActivity(Intent(this, StudentDashboardActivity::class.java))
        }

        findViewById<Button>(R.id.btnDoctor).setOnClickListener {
            startActivity(Intent(this, DoctorDashboardActivity::class.java))
        }

        findViewById<Button>(R.id.btnAdmin).setOnClickListener {
            // future admin panel
        }
    }
}