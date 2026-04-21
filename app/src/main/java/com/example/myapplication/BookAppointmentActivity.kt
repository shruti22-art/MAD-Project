package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class BookAppointmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_appointment)

        val name = findViewById<EditText>(R.id.etPatientName)
        val date = findViewById<EditText>(R.id.etDate)
        val time = findViewById<EditText>(R.id.etTime)
        val btn = findViewById<Button>(R.id.btnConfirm)

        //GET DOCTOR NAME
        val doctorName = intent.getStringExtra("doctorName")

        btn.setOnClickListener {

            val patient = name.text.toString()
            val d = date.text.toString()
            val t = time.text.toString()

            if (patient.isEmpty() || d.isEmpty() || t.isEmpty()) {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show()
            } else {

                val pref = getSharedPreferences("APPOINTMENTS", Context.MODE_PRIVATE)

                val currentToken = pref.getInt("token", 0) + 1
                val waitingTime = (currentToken - 1) * 10

                val newAppointment = "Doctor: $doctorName\nToken: $currentToken\n$patient\nDate: $d\nTime: $t\nWaiting: $waitingTime mins"

// 👉 Get old data
                val oldData = pref.getString("appointment", "") ?: ""

// 👉 Append instead of overwrite
                val updatedData = if (oldData.isEmpty()) {
                    newAppointment
                } else {
                    "$oldData\n\n$newAppointment"
                }

// 👉 Save
                pref.edit()
                    .putString("appointment", updatedData)
                    .putInt("token", currentToken)
                    .apply()
            }
        }
    }
}