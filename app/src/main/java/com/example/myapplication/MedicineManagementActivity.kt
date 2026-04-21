package com.example.myapplication

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MedicineManagementActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medicine_management)

        val etName = findViewById<EditText>(R.id.etMedicineName)
        val etDose = findViewById<EditText>(R.id.etDose)

        val btnView = findViewById<Button>(R.id.btnViewMedicine)
        val btnAdd = findViewById<Button>(R.id.btnAddMedicine)

        // 🔥 SAVE MEDICINE LOCALLY (NO NEW ACTIVITY)
        btnAdd.setOnClickListener {

            val name = etName.text.toString().trim()
            val dose = etDose.text.toString().trim()

            if (name.isEmpty() || dose.isEmpty()) {
                Toast.makeText(this, "Enter medicine details", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val sharedPref = getSharedPreferences("medicines", MODE_PRIVATE)
            val editor = sharedPref.edit()

            editor.putString("medicineName", name)
            editor.putString("medicineDose", dose)
            editor.apply()

            Toast.makeText(this, "Medicine Saved Locally", Toast.LENGTH_SHORT).show()

            etName.text.clear()
            etDose.text.clear()
        }

        // VIEW SCREEN
        btnView.setOnClickListener {
            startActivity(Intent(this, MedicineActivity::class.java))
        }
    }
}