package com.pavlitz.diary.Utils

import android.text.Editable
import android.text.TextWatcher

/**
 * interface which inherited from textwatcher to minimize code, and do own stuff ONLY for afterTextChanged
 */
interface TextChangedListener<T>:TextWatcher {
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun afterTextChanged(p0: Editable?) {
        if (p0 != null) {
            this.onTextChanged(p0)
        }
    }

    abstract fun onTextChanged(s:Editable)
}