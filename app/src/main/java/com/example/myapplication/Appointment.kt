package com.example.myapplication

data class Appointment(
    val appointmentId: String = "",
    val userId: String = "",
    val userName: String = "",
    val userEmail: String = "",
    val doctorName: String = "",
    val date: String = "",
    val time: String = "",
    val status: String = "booked",
    val timestamp: Long = 0L
)
