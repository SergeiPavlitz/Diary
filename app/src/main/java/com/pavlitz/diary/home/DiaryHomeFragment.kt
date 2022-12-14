package com.pavlitz.diary.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.pavlitz.diary.R
import com.pavlitz.diary.databinding.DiaryHomeFragmentLayoutBinding

class DiaryHomeFragment : Fragment() {

    private lateinit var viewModel: DiaryHomeViewModel

    private val args: DiaryHomeFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: DiaryHomeFragmentLayoutBinding = DataBindingUtil.inflate(
            inflater, R.layout.diary_home_fragment_layout, container, false
        )

        viewModel = ViewModelProvider(this)[DiaryHomeViewModel::class.java]

        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.diary_home_fragment_title)

        binding.addNewEntryButton.setOnClickListener { view:View ->
            val action = DiaryHomeFragmentDirections.actionDiaryHomeFragmentToEntryCreationFragment()
            view.findNavController().navigate(action)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val milli = args.dateMilli
        val topic = args.topic
        val body = args.body
        if (topic.isNotBlank() && body.isNotBlank()){
            //save entry
            Log.i("DiaryHomeFragment", " received $milli $topic $body" )
        }
    }
}