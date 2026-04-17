package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AdminAppointmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Make sure this layout name is EXACT
        setContentView(R.layout.activity_admin_appointment)

        val textView = findViewById<TextView>(R.id.tvAllAppointments)

        val pref = getSharedPreferences("APPOINTMENTS", Context.MODE_PRIVATE)
        val appointment = pref.getString("appointment", "No Appointments Found")

        textView.text = appointment
    }
}