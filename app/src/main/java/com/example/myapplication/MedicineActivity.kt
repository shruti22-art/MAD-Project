package com.example.myapplication

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MedicineActivity : AppCompatActivity() {

    lateinit var listView: ListView
    lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medicine)

        listView = findViewById(R.id.listViewMedicine)

        // default data (only first time)
        if (MedicineData.medicineList.isEmpty()) {
            MedicineData.medicineList.addAll(
                listOf(
                    "Paracetamol (Qty: 10)",
                    "Dolo 650 (Qty: 5)",
                    "Crocin (Qty: 2)",
                    "Ibuprofen (Qty: 15)"
                )
            )
        }

        adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            MedicineData.medicineList
        )

        listView.adapter = adapter

        // DELETE OPTION (LONG CLICK)
        listView.setOnItemLongClickListener { _, _, position, _ ->

            AlertDialog.Builder(this)
                .setTitle("Delete Medicine")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes") { _, _ ->
                    MedicineData.medicineList.removeAt(position)
                    adapter.notifyDataSetChanged()
                    Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("No", null)
                .show()

            true
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }
}