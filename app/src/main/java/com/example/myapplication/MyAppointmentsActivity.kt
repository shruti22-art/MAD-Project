package com.example.myapplication

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class MyAppointmentsActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var list: ArrayList<String>
    private lateinit var adapter: ArrayAdapter<String>
    private val appointmentIds = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_appointments)

        listView = findViewById(R.id.listView)
        list = ArrayList()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
        listView.adapter = adapter

        val userId = FirebaseAuth.getInstance().currentUser?.uid

        if (userId != null) {
            val ref = FirebaseDatabase.getInstance().getReference("appointments")

            ref.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    list.clear()
                    appointmentIds.clear()

                    for (data in snapshot.children) {
                        val appointment = data.getValue(Appointment::class.java) ?: continue
                        if (appointment.userId != userId) continue
                        appointmentIds.add(appointment.appointmentId)
                        list.add(
                            "Dr: ${appointment.doctorName}\n" +
                                "${appointment.date} at ${appointment.time}\n" +
                                "Status: ${appointment.status}"
                        )
                    }

                    adapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(
                        this@MyAppointmentsActivity,
                        "Failed to fetch appointments: ${error.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        } else {
            Toast.makeText(this, "Please login first.", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}