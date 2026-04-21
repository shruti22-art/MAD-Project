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

        val etName = findViewById<EditText>(R.id.etName)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val spinnerRole = findViewById<Spinner>(R.id.spinnerRole)
        val btnSignup = findViewById<Button>(R.id.btnSignup)

        val roles = arrayOf("Student", "Doctor", "Admin")
        spinnerRole.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, roles)

        btnSignup.setOnClickListener {

            val email = etEmail.text.toString().trim().lowercase()
            val password = etPassword.text.toString().trim()
            val role = spinnerRole.selectedItem.toString()

            val pref = getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
            val editor = pref.edit()

            // Store data locally
            editor.putString(email, "$password,$role")
            editor.apply()

            Toast.makeText(this, "Signup Successful", Toast.LENGTH_SHORT).show()

            // Optional: go to login screen
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}