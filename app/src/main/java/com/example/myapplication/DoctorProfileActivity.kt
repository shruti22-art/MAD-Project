package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class DoctorProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_profile)

        val name = findViewById<TextView>(R.id.tvName)
        val spec = findViewById<TextView>(R.id.tvSpec)
        val btnBook = findViewById<Button>(R.id.btnBookNow)

        val docName = intent.getStringExtra("name")
        val docSpec = intent.getStringExtra("specialization")

        name.text = docName
        spec.text = docSpec

        btnBook.setOnClickListener {

            val intent = Intent(this, DoctorTimingActivity::class.java)
            intent.putExtra("doctorName", docName)
            intent.putExtra("specialization", docSpec)
            startActivity(intent)
        }
    }
}