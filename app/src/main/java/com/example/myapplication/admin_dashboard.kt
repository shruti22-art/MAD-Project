package com.example.myapplication

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.jvm.java

class AdminDashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_dashboard)

        val btnStudents = findViewById<Button>(R.id.btnStudents)
        val btnDoctors = findViewById<Button>(R.id.btnDoctors)
        val btnMedicine = findViewById<Button>(R.id.btnMedicine)
        val btnAppointments = findViewById<Button>(R.id.btnAppointments)
        val btnBlogs = findViewById<Button>(R.id.btnBlogs)
        val btnDoctorTiming = findViewById<Button>(R.id.btnDoctorTiming)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        // 👨‍🎓 View Students
        btnStudents.setOnClickListener {
            startActivity(Intent(this, StudentListActivity::class.java))
        }

        // 👨‍⚕️ View Doctors
        btnDoctors.setOnClickListener {
            startActivity(Intent(this, DoctorListActivity::class.java))
        }

        // 💊 Medicine Management
        btnMedicine.setOnClickListener {
            startActivity(Intent(this, MedicineActivity::class.java))
        }

        // 📅 Appointment Management
        btnAppointments.setOnClickListener {
            startActivity(Intent(this, AdminAppointmentActivity::class.java))
        }

        // 📝 Blog Management
        btnBlogs.setOnClickListener {
            startActivity(Intent(this, BlogActivity::class.java))
        }

        btnDoctorTiming.setOnClickListener {
            Toast.makeText(this, "Button Clicked", Toast.LENGTH_SHORT).show()

            startActivity(Intent(this, DoctorTimingActivity::class.java))
        }

        // 🚪 Logout
        btnLogout.setOnClickListener {

            val pref: SharedPreferences = getSharedPreferences("USER_DATA", MODE_PRIVATE)
            val editor = pref.edit()
            editor.clear()
            editor.apply()

            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}




