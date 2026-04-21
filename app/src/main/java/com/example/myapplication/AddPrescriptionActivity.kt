package com.example.myapplication
import android.content.Context
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class AddPrescriptionActivity : AppCompatActivity() {

    private lateinit var etPatientName: EditText
    private lateinit var etDiagnosis: EditText
    private lateinit var etMedicines: EditText
    private lateinit var etNotes: EditText
    private lateinit var btnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_prescription)

        etPatientName = findViewById(R.id.etPatientName)
        etDiagnosis = findViewById(R.id.etDiagnosis)
        etMedicines = findViewById(R.id.etMedicines)
        etNotes = findViewById(R.id.etNotes)
        btnSave = findViewById(R.id.btnSave)

        btnSave.setOnClickListener {

            val name = etPatientName.text.toString()
            val diagnosis = etDiagnosis.text.toString()
            val medicines = etMedicines.text.toString()
            val notes = etNotes.text.toString()

            if (name.isEmpty() || diagnosis.isEmpty()) {
                Toast.makeText(this, "Please fill required fields", Toast.LENGTH_SHORT).show()
            } else {
                val prescriptionText =
                    "Patient: $name\nDiagnosis: $diagnosis\nMedicines: $medicines\nNotes: $notes"

                val pref = getSharedPreferences("PRESCRIPTION_DATA", Context.MODE_PRIVATE)
                val oldData = pref.getString("PRESCRIPTION_LIST", "")

                val newData = if (oldData!!.isEmpty()) {
                    prescriptionText
                } else {
                    "$oldData|$prescriptionText"
                }

                pref.edit().putString("PRESCRIPTION_LIST", newData).apply()
                Toast.makeText(this, "Prescription Saved", Toast.LENGTH_SHORT).show()

                // Clear fields
                etPatientName.text.clear()
                etDiagnosis.text.clear()
                etMedicines.text.clear()
                etNotes.text.clear()
            }
        }
    }
}