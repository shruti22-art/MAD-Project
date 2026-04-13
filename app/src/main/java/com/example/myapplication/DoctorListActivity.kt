package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class DoctorListActivity : AppCompatActivity() {

    lateinit var listView: ListView
    lateinit var search: EditText
    lateinit var doctorList: ArrayList<Doctor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_list)

        listView = findViewById(R.id.listDoctors)
        search = findViewById(R.id.etSearchDoctor)

        doctorList = arrayListOf(
            Doctor("Dr. Sharma", "Cardiologist"),
            Doctor("Dr. Mehta", "Dermatologist"),
            Doctor("Dr. Singh", "Dentist"),
            Doctor("Dr. Verma", "General Physician")
        )

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            doctorList.map { "${it.name} - ${it.specialization}" }
        )

        listView.adapter = adapter

        // Search
        search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val filtered = doctorList.filter {
                    it.name.lowercase().contains(s.toString().lowercase())
                }

                val newAdapter = ArrayAdapter(
                    this@DoctorListActivity,
                    android.R.layout.simple_list_item_1,
                    filtered.map { "${it.name} - ${it.specialization}" }
                )

                listView.adapter = newAdapter
            }
        })


        listView.setOnItemClickListener { _, _, position, _ ->

            val selectedDoctor = doctorList[position]

            val intent = Intent(this, DoctorProfileActivity::class.java)
            intent.putExtra("name", selectedDoctor.name)
            intent.putExtra("specialization", selectedDoctor.specialization)

            startActivity(intent)
        }
    }
}