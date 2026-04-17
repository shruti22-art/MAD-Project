package com.example.myapplication

import android.content.Context

object MedicineStorage {

    fun saveMedicine(context: Context, name: String, desc: String, qty: String) {
        val pref = context.getSharedPreferences("MEDICINE", Context.MODE_PRIVATE)
        val editor = pref.edit()

        val oldData = pref.getString("data", "")

        val newEntry = "$name|$desc|$qty;"

        editor.putString("data", oldData + newEntry)
        editor.apply()
    }

    fun getMedicines(context: Context): ArrayList<MedicineModel> {
        val list = ArrayList<MedicineModel>()
        val pref = context.getSharedPreferences("MEDICINE", Context.MODE_PRIVATE)

        val data = pref.getString("data", "")

        if (!data.isNullOrEmpty()) {
            val items = data.split(";")

            for (item in items) {
                if (item.isNotEmpty()) {
                    val parts = item.split("|")
                    list.add(MedicineModel(parts[0], parts[1], parts[2]))
                }
            }
        }
        return list
    }

    fun clearAll(context: Context) {
        val pref = context.getSharedPreferences("MEDICINE", Context.MODE_PRIVATE)
        pref.edit().clear().apply()
    }
}