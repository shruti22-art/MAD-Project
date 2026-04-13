package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val email = findViewById<EditText>(R.id.etEmail)
        val password = findViewById<EditText>(R.id.etPassword)
        val spinner = findViewById<Spinner>(R.id.spinnerRole)
        val btnSignup = findViewById<Button>(R.id.btnSignup)

        val roles = arrayOf("Student", "Doctor", "Admin")
        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, roles)

        btnSignup.setOnClickListener {

            val userEmail = email.text.toString().trim()
            val userPass = password.text.toString().trim()
            val role = spinner.selectedItem.toString()

            if (userEmail.isEmpty() || userPass.isEmpty()) {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show()
            } else {

                val pref = getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
                val editor = pref.edit()

                // 🔥 MULTIPLE USERS STORE
                editor.putString(userEmail, "$userPass,$role")
                editor.apply()

                Toast.makeText(this, "Signup Successful", Toast.LENGTH_SHORT).show()

                // 🔥 DIRECT DASHBOARD
                when (role) {
                    "Admin" -> startActivity(Intent(this, AdminDashboardActivity::class.java))
                    "Doctor" -> startActivity(Intent(this, DoctorDashboardActivity::class.java))
                    "Student" -> startActivity(Intent(this, StudentDashboardActivity::class.java))
                }

                finish()
            }
        }
    }
}