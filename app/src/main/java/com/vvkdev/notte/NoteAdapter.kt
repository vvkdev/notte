package com.vvkdev.notte

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vvkdev.notte.databinding.RvItemBinding
import com.vvkdev.notte.db.Note

class NoteAdapter(private val list: MutableList<Note>) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    class NoteViewHolder(val binding: RvItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemCount() = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RvItemBinding.inflate(inflater, parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        with(holder.binding) {
            tvTitle.text = list[position].title
            tvContent.text = list[position].content
        }
    }

    fun updateAdapter(listItems: List<Note>) {
        list.clear()
        list.addAll(listItems)
        notifyDataSetChanged()
    }
}