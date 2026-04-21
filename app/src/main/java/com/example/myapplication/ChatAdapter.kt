package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatAdapter(
    private val messages: List<Message>,
    private val currentUserId: String
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_RIGHT = 1
    private val VIEW_TYPE_LEFT = 2

    override fun getItemViewType(position: Int): Int {
        return if (messages[position].senderId == currentUserId) VIEW_TYPE_RIGHT else VIEW_TYPE_LEFT
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if (viewType == VIEW_TYPE_RIGHT) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_message_right, parent, false)
            RightViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_message_left, parent, false)
            LeftViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = messages[position]

        if (holder is RightViewHolder) {
            holder.text.text = message.text
        } else if (holder is LeftViewHolder) {
            holder.text.text = message.text
        }
    }

    override fun getItemCount(): Int = messages.size

    class RightViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text: TextView = itemView.findViewById(R.id.tvMessageRight)
    }

    class LeftViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text: TextView = itemView.findViewById(R.id.tvMessageLeft)
    }
}