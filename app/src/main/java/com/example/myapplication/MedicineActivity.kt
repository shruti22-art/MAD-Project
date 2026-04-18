package com.example.myapplication

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MedicineActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medicine)

        val listView = findViewById<ListView>(R.id.listViewMedicine)

        val medicines = arrayOf(
            "Paracetamol",
            "Dolo 650",
            "Crocin",
            "Azithromycin",
            "Amoxicillin",
            "Ibuprofen",
            "Cetirizine"
        )

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            medicines
        )

        listView.adapter = adapter
    }
}