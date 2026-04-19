package com.example.myapplication

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class AddMedicineActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_medicine)

        val name = findViewById<EditText>(R.id.etName)
        val qty = findViewById<EditText>(R.id.etQty)
        val btn = findViewById<Button>(R.id.btnAdd)

        btn.setOnClickListener {

            val medName = name.text.toString().trim()
            val quantity = qty.text.toString().trim()

            if (medName.isEmpty() || quantity.isEmpty()) {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val qtyInt = quantity.toInt()

            // ADD TO SHARED LIST
            MedicineData.medicineList.add("$medName (Qty: $qtyInt)")

            // LOW STOCK WARNING
            if (qtyInt < 5) {
                Toast.makeText(this, "⚠ Low Stock!", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Medicine Added", Toast.LENGTH_SHORT).show()
            }

            name.setText("")
            qty.setText("")
        }
    }
}