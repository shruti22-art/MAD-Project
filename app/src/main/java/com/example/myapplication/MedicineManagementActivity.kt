package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MedicineManagementActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medicine_management)

        val btnView = findViewById<Button>(R.id.btnViewMedicine)
        val btnAdd = findViewById<Button>(R.id.btnAddMedicine)

        //View Medicines
        btnView.setOnClickListener {
            startActivity(Intent(this, MedicineActivity::class.java))
        }

        //Add Medicine
        btnAdd.setOnClickListener {
            startActivity(Intent(this, AddMedicineActivity::class.java))
        }
    }
}