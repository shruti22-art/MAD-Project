package com.example.myapplication

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class UploadReportActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_report)

        val etReport = findViewById<EditText>(R.id.etReport)
        val btnUpload = findViewById<Button>(R.id.btnUpload)

        btnUpload.setOnClickListener {
            val text = etReport.text.toString()

            if (text.isNotEmpty()) {
                Toast.makeText(this, "Report Uploaded", Toast.LENGTH_SHORT).show()
                etReport.text.clear()
            } else {
                Toast.makeText(this, "Enter report first", Toast.LENGTH_SHORT).show()
            }
        }
    }
}