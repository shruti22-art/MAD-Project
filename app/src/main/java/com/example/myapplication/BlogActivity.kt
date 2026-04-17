package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class BlogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blog)

        val title = findViewById<EditText>(R.id.etTitle)
        val content = findViewById<EditText>(R.id.etContent)
        val btn = findViewById<Button>(R.id.btnSaveBlog)
        val text = findViewById<TextView>(R.id.tvBlogs)

        val pref = getSharedPreferences("BLOGS", Context.MODE_PRIVATE)

        // ✅ FIXED (nullable issue solved)
        text.text = pref.getString("data", "No Blogs Yet") ?: "No Blogs Yet"

        btn.setOnClickListener {

            val t = title.text.toString().trim()
            val c = content.text.toString().trim()

            if (t.isEmpty() || c.isEmpty()) {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show()
            } else {

                val old = pref.getString("data", "") ?: ""
                val newData = "$old\n\nTitle: $t\n$c"

                pref.edit().putString("data", newData).apply()

                text.text = newData

                title.text.clear()
                content.text.clear()

                Toast.makeText(this, "Blog Saved", Toast.LENGTH_SHORT).show()
            }
        }
    }
}