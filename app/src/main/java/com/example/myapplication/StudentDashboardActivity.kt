package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class StudentDashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_dashboard)

        val doctors = findViewById<Button>(R.id.btnDoctors)
        val medicines = findViewById<Button>(R.id.btnMedicines)
        val myAppointments = findViewById<Button>(R.id.btnMyAppointments)
        val logout = findViewById<Button>(R.id.btnLogout)   // 🔥 NEW

        doctors.setOnClickListener {
            startActivity(Intent(this, DoctorListActivity::class.java))
        }

        medicines.setOnClickListener {
            startActivity(Intent(this, MedicineActivity::class.java))
        }

        myAppointments.setOnClickListener {
            startActivity(Intent(this, MyAppointmentsActivity::class.java))
        }

        //LOGOUT FUNCTION
        logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}