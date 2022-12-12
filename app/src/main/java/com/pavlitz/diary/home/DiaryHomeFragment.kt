package com.pavlitz.diary.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.pavlitz.diary.R
import com.pavlitz.diary.databinding.DiaryFragmentLayoutBinding
import com.pavlitz.diary.entryCreation.EntryCreationFragment

class DiaryHomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: DiaryFragmentLayoutBinding = DataBindingUtil.inflate(
            inflater, R.layout.diary_fragment_layout, container, false
        )

        binding.addNewEntryButton.setOnClickListener { view ->
            // TODO: add navigation
            val creationFragment = EntryCreationFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(this.id, creationFragment)
                .addToBackStack("ok")
                .commit()
        }

        return binding.root
    }

}