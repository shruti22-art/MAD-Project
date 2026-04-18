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

        // LOGIN
        btnLogin.setOnClickListener {

            val userEmail = email.text.toString().trim()
            val userPass = password.text.toString().trim()

            val pref = getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
            val data = pref.getString(userEmail, null)

            if (data != null) {

                val parts = data.split(",")
                val savedPass = parts[0]
                val role = parts[1]

                if (userPass == savedPass) {

                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()

                    when (role) {
                        "Admin" -> startActivity(Intent(this, AdminDashboardActivity::class.java))
                        "Doctor" -> startActivity(Intent(this, DoctorDashboardActivity::class.java))
                        "Student" -> startActivity(Intent(this, StudentDashboardActivity::class.java))
                    }

                } else {
                    Toast.makeText(this, "Wrong Password", Toast.LENGTH_SHORT).show()
                }

            } else {
                Toast.makeText(this, "User not found. Please Signup", Toast.LENGTH_SHORT).show()
            }
        }

        // 🔥 SIGNUP BUTTON
        goSignup.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }
}