package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class StudentDashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_dashboard)

        val doctors = findViewById<Button>(R.id.btnDoctors)
        val medicines = findViewById<Button>(R.id.btnMedicines)
        val myAppointments = findViewById<Button>(R.id.btnMyAppointments)

        doctors.setOnClickListener {
            startActivity(Intent(this, DoctorListActivity::class.java))
        }

        medicines.setOnClickListener {
            startActivity(Intent(this, MedicineActivity::class.java))
        }

        myAppointments.setOnClickListener {
            startActivity(Intent(this, MyAppointmentsActivity::class.java))
        }
    }
}