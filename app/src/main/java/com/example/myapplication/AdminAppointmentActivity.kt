package com.example.myapplication

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AdminAppointmentActivity : AppCompatActivity() {

    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_appointment)

        textView = findViewById(R.id.tvAllAppointments)
        val ref = FirebaseDatabase.getInstance().getReference("appointments")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (!snapshot.exists()) {
                    textView.text = "No appointments found."
                    return
                }

                val lines = StringBuilder()
                for (child in snapshot.children) {
                    val item = child.getValue(Appointment::class.java) ?: continue
                    val ts = if (item.timestamp > 0L) {
                        SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault())
                            .format(Date(item.timestamp))
                    } else "N/A"
                    lines.append("Doctor: ${item.doctorName}\n")
                        .append("Patient: ${item.userName.ifBlank { item.userEmail }}\n")
                        .append("Slot: ${item.date} ${item.time}\n")
                        .append("Created: $ts\n")
                        .append("Status: ${item.status}\n\n")
                }
                textView.text = lines.toString().trim()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(
                    this@AdminAppointmentActivity,
                    "Failed to load appointments: ${error.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}