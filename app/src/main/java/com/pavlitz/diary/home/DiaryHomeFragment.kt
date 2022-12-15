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
import androidx.recyclerview.widget.LinearLayoutManager
import com.pavlitz.diary.R
import com.pavlitz.diary.database.DiaryDataBase
import com.pavlitz.diary.databinding.DiaryHomeFragmentLayoutBinding

class DiaryHomeFragment : Fragment() {

    private lateinit var viewModel: DiaryHomeViewModel

    private lateinit var binding: DiaryHomeFragmentLayoutBinding

    private val args: DiaryHomeFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.diary_home_fragment_layout, container, false
        )
        // Create an instance of the ViewModel Factory.
        val application = requireNotNull(this.activity).application
        val dataSource = DiaryDataBase.getInstance(application).diaryDataBaseDao
        val viewModelFactory = DiaryHomeViewModelFactory(dataSource)
        viewModel = ViewModelProvider(this, viewModelFactory)[DiaryHomeViewModel::class.java]

        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.diary_home_fragment_title)

        binding.addNewEntryButton.setOnClickListener { view: View ->
            val action =
                DiaryHomeFragmentDirections.actionDiaryHomeFragmentToEntryCreationFragment()
            view.findNavController().navigate(action)
        }

        val adapter = DiaryEntryAdapter(viewModel.getItemViewList())
        val manager = LinearLayoutManager(activity)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = manager

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val topic = args.topic
        val body = args.body
        val dateMilli = args.dateMilli
        if (topic != null && body != null) {
            if (topic.isNotBlank() && body.isNotBlank()) {
                //save entry
                viewModel.saveEntry(dateMilli, topic, body)

                Log.i("DiaryHomeFragment", " received $dateMilli $topic $body")
            }
        }

    }
}