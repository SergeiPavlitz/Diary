package com.pavlitz.diary.home

import com.pavlitz.diary.database.DiaryItemView

class DiaryItemViewListener(val clickListener: (id:Long) -> Unit) {
    fun onClick(item: DiaryItemView) = clickListener(item.id)
}