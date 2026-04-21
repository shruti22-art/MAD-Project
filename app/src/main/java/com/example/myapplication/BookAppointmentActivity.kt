package com.example.myapplication

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class BookAppointmentActivity : AppCompatActivity() {

    private lateinit var doctorEdit: EditText
    private lateinit var dateEdit: EditText
    private lateinit var timeEdit: EditText
    private lateinit var btnBook: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_appointment)

        doctorEdit = findViewById(R.id.etPatientName)
        dateEdit = findViewById(R.id.etDate)
        timeEdit = findViewById(R.id.etTime)
        btnBook = findViewById(R.id.btnConfirm)
        doctorEdit.setText(intent.getStringExtra("doctorName").orEmpty())

        btnBook.setOnClickListener {
            val doctor = doctorEdit.text.toString().trim()
            val date = dateEdit.text.toString().trim()
            val time = timeEdit.text.toString().trim()

            val userId = FirebaseAuth.getInstance().currentUser?.uid
            val userEmail = FirebaseAuth.getInstance().currentUser?.email.orEmpty()
            val userName = FirebaseAuth.getInstance().currentUser?.displayName.orEmpty()

            if (doctor.isBlank() || date.isBlank() || time.isBlank()) {
                Toast.makeText(this, "Please fill all appointment fields.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (userId != null) {
                val ref = FirebaseDatabase.getInstance()
                    .getReference("appointments")

                val appointmentId = ref.push().key!!
                val appointment = Appointment(
                    appointmentId = appointmentId,
                    userId = userId,
                    userName = userName,
                    userEmail = userEmail,
                    doctorName = doctor,
                    date = date,
                    time = time,
                    status = "booked",
                    timestamp = System.currentTimeMillis()
                )

                ref.child(appointmentId).setValue(appointment)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Appointment Booked", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Failed to book appointment.", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Please login first.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}