package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ChatActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var etMessage: EditText
    private lateinit var btnSend: Button

    private val messageList = ArrayList<Message>()
    private lateinit var adapter: ChatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        recyclerView = findViewById(R.id.chatRecyclerView)
        etMessage = findViewById(R.id.etMessage)
        btnSend = findViewById(R.id.btnSend)

        adapter = ChatAdapter(messageList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // 🔥 Test message (patient - left side)
        messageList.add(Message("Hello Doctor", false))

        // ✅ SEND BUTTON (CORRECT PLACE)
        btnSend.setOnClickListener {

            val text = etMessage.text.toString()

            if (text.isNotEmpty()) {

                val message = Message(text, true) // doctor

                messageList.add(message)

                adapter.notifyDataSetChanged()

                etMessage.text.clear()

                recyclerView.scrollToPosition(messageList.size - 1)
            }
        }
    }
}