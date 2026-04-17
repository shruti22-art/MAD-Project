package com.example.myapplication

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MedicineActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medicine)

        val etName = findViewById<EditText>(R.id.etName)
        val etDesc = findViewById<EditText>(R.id.etDesc)
        val etQty = findViewById<EditText>(R.id.etQty)
        val btnAdd = findViewById<Button>(R.id.btnAdd)

        btnAdd.setOnClickListener {

            val name = etName.text.toString().trim()
            val desc = etDesc.text.toString().trim()
            val qty = etQty.text.toString().trim()

            if (name.isEmpty() || desc.isEmpty() || qty.isEmpty()) {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(this, "Medicine Added Successfully", Toast.LENGTH_SHORT).show()

            etName.setText("")
            etDesc.setText("")
            etQty.setText("")
        }
    }
}