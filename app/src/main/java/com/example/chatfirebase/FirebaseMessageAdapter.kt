package com.example.chatfirebase

import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chatfirebase.databinding.ItemMessageBinding
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class FirebaseMessageAdapter(
    options: FirebaseRecyclerOptions<Message>,
    private val currentUser: String?
) : FirebaseRecyclerAdapter<Message, FirebaseMessageAdapter.MessageViewHolder>(options){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FirebaseMessageAdapter.MessageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_message, parent, false)
        val binding = ItemMessageBinding.bind(view)
        return MessageViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: FirebaseMessageAdapter.MessageViewHolder,
        position: Int,
        model: Message
    ) {
        holder.bind(model)
    }

    inner class MessageViewHolder(private val binding : ItemMessageBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Message) {
            binding.tvMessage.text = item.text
            setTextColor(item.name, binding.tvMessage)
            binding.tvMessenger.text = item.name
            if (item.timestamp != null) {
                binding.tvTimestamp.text = DateUtils.getRelativeTimeSpanString(item.timestamp)
            }
        }
    }

    private fun setTextColor(userName: String?, textView: TextView) {
        if (currentUser == userName && userName != null) {
            textView.setBackgroundResource(R.drawable.rounded_message_primary)
        } else {
            textView.setBackgroundResource(R.drawable.rounded_message_secondary)
        }
    }
}