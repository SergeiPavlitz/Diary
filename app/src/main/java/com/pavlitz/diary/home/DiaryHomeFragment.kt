package com.pavlitz.diary.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.pavlitz.diary.R
import com.pavlitz.diary.databinding.DiaryHomeFragmentLayoutBinding

class DiaryHomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: DiaryHomeFragmentLayoutBinding = DataBindingUtil.inflate(
            inflater, R.layout.diary_home_fragment_layout, container, false
        )

        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.diary_home_fragment_title)

        binding.addNewEntryButton.setOnClickListener { view:View ->
            val action = DiaryHomeFragmentDirections.actionDiaryHomeFragmentToEntryCreationFragment()
            view.findNavController().navigate(action)
        }

        return binding.root
    }

}