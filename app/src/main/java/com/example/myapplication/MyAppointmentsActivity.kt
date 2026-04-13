package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MyAppointmentsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_appointments)

        val text = findViewById<TextView>(R.id.tvAppointment)
        val cancelBtn = findViewById<Button>(R.id.btnCancel)

        val pref = getSharedPreferences("APPOINTMENTS", Context.MODE_PRIVATE)

        val appointment = pref.getString("appointment", "No Appointments")

        text.text = appointment

        cancelBtn.setOnClickListener {

            pref.edit().remove("appointment").apply()

            Toast.makeText(this, "Appointment Cancelled", Toast.LENGTH_SHORT).show()

            text.text = "No Appointments"
        }
    }
}