package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val email = findViewById<EditText>(R.id.etEmail)
        val password = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val goSignup = findViewById<Button>(R.id.btnGoSignup)

        btnLogin.setOnClickListener {

            val userEmail = email.text.toString().trim().lowercase()
            val userPass = password.text.toString().trim()

            val pref = getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
            val data = pref.getString(userEmail, null)

            // 🔥 DEBUG (you will SEE what is stored)
            Toast.makeText(this, "DATA = $data", Toast.LENGTH_LONG).show()

            if (data == null) {
                Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val parts = data.split(",")

            if (parts.size < 2) {
                Toast.makeText(this, "Corrupted Data", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val savedPass = parts[0]
            val role = parts[1].trim()

            // 🔥 DEBUG ROLE
            Toast.makeText(this, "ROLE = $role", Toast.LENGTH_LONG).show()

            if (userPass == savedPass) {

                if (role.equals("Doctor", true)) {
                    startActivity(Intent(this, DoctorDashboardActivity::class.java))

                } else if (role.equals("Student", true)) {
                    startActivity(Intent(this, StudentDashboardActivity::class.java))

                } else if (role.equals("Admin", true)) {
                    startActivity(Intent(this, AdminDashboardActivity::class.java))

                } else {
                    Toast.makeText(this, "Invalid Role: $role", Toast.LENGTH_SHORT).show()
                }

                finish()

            } else {
                Toast.makeText(this, "Wrong Password", Toast.LENGTH_SHORT).show()
            }
        }

        goSignup.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }
}