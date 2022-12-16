package com.pavlitz.diary.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pavlitz.diary.R
import com.pavlitz.diary.database.DiaryItemView
import java.text.SimpleDateFormat
import java.util.*


class DiaryEntryAdapter():
    ListAdapter<DiaryItemView, DiaryEntryAdapter.ViewHolder>(DiaryItemDiffCallback()){


    private val simpleFormatter = SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_diary_entry, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemViewModel = getItem(position)
        val dateString = simpleFormatter.format(itemViewModel.date)
        holder.date.text = dateString
        holder.topic.text = itemViewModel.topic
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val date: TextView = view.findViewById(R.id.list_item_date)
        val topic: TextView = view.findViewById(R.id.list_item_topic)
    }


    class DiaryItemDiffCallback : DiffUtil.ItemCallback<DiaryItemView>() {

        override fun areItemsTheSame(oldItem: DiaryItemView, newItem: DiaryItemView): Boolean {
            return oldItem.id == newItem.id
        }


        override fun areContentsTheSame(oldItem: DiaryItemView, newItem: DiaryItemView): Boolean {
            return oldItem == newItem
        }
    }
}

