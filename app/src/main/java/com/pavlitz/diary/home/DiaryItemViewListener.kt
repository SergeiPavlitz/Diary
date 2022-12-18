package com.pavlitz.diary.home

import com.pavlitz.diary.database.diary.DiaryItemView

class DiaryItemViewListener(val clickListener: (id:Long) -> Unit) {
    fun onClick(item: DiaryItemView) = clickListener(item.id)
}