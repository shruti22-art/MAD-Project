package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SignupActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        auth = FirebaseAuth.getInstance()

        val etName = findViewById<EditText>(R.id.etName)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val spinnerRole = findViewById<Spinner>(R.id.spinnerRole)
        val btnSignup = findViewById<Button>(R.id.btnSignup)

        val roles = arrayOf("Student", "Doctor", "Admin")
        spinnerRole.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, roles)

        btnSignup.setOnClickListener {
            val name = etName.text.toString().trim()
            val email = etEmail.text.toString().trim().lowercase()
            val password = etPassword.text.toString().trim()
            val role = spinnerRole.selectedItem.toString()

            if (name.isBlank() || email.isBlank() || password.length < 6) {
                Toast.makeText(
                    this,
                    "Enter name, valid email and password (min 6 chars).",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            btnSignup.isEnabled = false
            auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener { result ->
                    val uid = result.user?.uid ?: return@addOnSuccessListener
                    val user = User(uid = uid, name = name, email = email, role = role)
                    FirebaseDatabase.getInstance()
                        .getReference("users")
                        .child(uid)
                        .setValue(user)
                        .addOnSuccessListener {
                            Toast.makeText(this, "Signup successful.", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, LoginActivity::class.java))
                            finish()
                        }
                        .addOnFailureListener {
                            btnSignup.isEnabled = true
                            Toast.makeText(this, "User created but profile save failed.", Toast.LENGTH_SHORT).show()
                        }
                }
                .addOnFailureListener { e ->
                    btnSignup.isEnabled = true
                    Toast.makeText(this, e.message ?: "Signup failed.", Toast.LENGTH_SHORT).show()
                }
        }
    }
}