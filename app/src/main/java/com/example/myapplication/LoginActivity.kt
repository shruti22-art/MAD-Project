package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()

        val email = findViewById<EditText>(R.id.etEmail)
        val password = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val goSignup = findViewById<Button>(R.id.btnGoSignup)

        btnLogin.setOnClickListener {
            val userEmail = email.text.toString().trim().lowercase()
            val userPass = password.text.toString().trim()
            if (userEmail.isBlank() || userPass.isBlank()) {
                Toast.makeText(this, "Email and password are required.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            btnLogin.isEnabled = false
            auth.signInWithEmailAndPassword(userEmail, userPass)
                .addOnSuccessListener { result ->
                    val uid = result.user?.uid ?: return@addOnSuccessListener
                    FirebaseDatabase.getInstance()
                        .getReference("users")
                        .child(uid)
                        .get()
                        .addOnSuccessListener { snapshot ->
                            btnLogin.isEnabled = true
                            val role = snapshot.child("role").getValue(String::class.java).orEmpty()
                            when (role.lowercase()) {
                                "doctor" -> startActivity(Intent(this, DoctorDashboardActivity::class.java))
                                "student" -> startActivity(Intent(this, StudentDashboardActivity::class.java))
                                "admin" -> startActivity(Intent(this, AdminDashboardActivity::class.java))
                                else -> {
                                    Toast.makeText(this, "Role not configured for this user.", Toast.LENGTH_SHORT).show()
                                    return@addOnSuccessListener
                                }
                            }
                            finish()
                        }
                        .addOnFailureListener {
                            btnLogin.isEnabled = true
                            Toast.makeText(this, "Login succeeded, but role fetch failed.", Toast.LENGTH_SHORT).show()
                        }
                }
                .addOnFailureListener { e ->
                    btnLogin.isEnabled = true
                    Toast.makeText(this, e.message ?: "Login failed.", Toast.LENGTH_SHORT).show()
                }
        }

        goSignup.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }
}