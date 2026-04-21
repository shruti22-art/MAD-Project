package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ChatActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var etMessage: EditText
    private lateinit var btnSend: Button
    private lateinit var tvTitle: TextView

    private val messageList = ArrayList<Message>()
    private lateinit var adapter: ChatAdapter
    private lateinit var auth: FirebaseAuth
    private lateinit var roomRef: DatabaseReference
    private lateinit var roomId: String
    private var chatListener: ValueEventListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        recyclerView = findViewById(R.id.chatRecyclerView)
        etMessage = findViewById(R.id.etMessage)
        btnSend = findViewById(R.id.btnSend)
        tvTitle = findViewById(R.id.tvChatTitle)

        auth = FirebaseAuth.getInstance()
        val currentUserId = auth.currentUser?.uid
        if (currentUserId == null) {
            Toast.makeText(this, "Please login again.", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        val peerId = intent.getStringExtra("peerId").orEmpty()
        val peerName = intent.getStringExtra("peerName").orEmpty()
        roomId = if (peerId.isNotBlank()) {
            listOf(currentUserId, peerId).sorted().joinToString("_")
        } else {
            "general_room"
        }
        tvTitle.text = if (peerName.isNotBlank()) "Chat with $peerName" else "Realtime Chat"
        roomRef = FirebaseDatabase.getInstance().getReference("chatRooms").child(roomId).child("messages")

        adapter = ChatAdapter(messageList, currentUserId)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        attachMessagesListener()
        btnSend.setOnClickListener {
            sendMessage(currentUserId)
        }
    }

    private fun attachMessagesListener() {
        chatListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                messageList.clear()
                for (child in snapshot.children) {
                    val message = child.getValue(Message::class.java) ?: continue
                    messageList.add(message)
                }
                messageList.sortBy { it.timestamp }
                adapter.notifyDataSetChanged()
                if (messageList.isNotEmpty()) {
                    recyclerView.scrollToPosition(messageList.lastIndex)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(
                    this@ChatActivity,
                    "Unable to load messages: ${error.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        roomRef.addValueEventListener(chatListener as ValueEventListener)
    }

    private fun sendMessage(currentUserId: String) {
        val text = etMessage.text.toString().trim()
        if (text.isEmpty()) return

        val messageId = roomRef.push().key ?: return
        val senderName = auth.currentUser?.displayName ?: auth.currentUser?.email.orEmpty()
        val message = Message(
            messageId = messageId,
            roomId = roomId,
            senderId = currentUserId,
            senderName = senderName,
            text = text,
            timestamp = System.currentTimeMillis()
        )

        roomRef.child(messageId).setValue(message)
            .addOnSuccessListener { etMessage.text.clear() }
            .addOnFailureListener {
                Toast.makeText(this, "Failed to send message.", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        chatListener?.let { roomRef.removeEventListener(it) }
    }
}