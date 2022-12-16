package com.pavlitz.diary.home

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pavlitz.diary.R
import com.pavlitz.diary.database.DiaryDataBase
import com.pavlitz.diary.databinding.DiaryHomeFragmentLayoutBinding

class DiaryHomeFragment : Fragment() {

    private lateinit var viewModel: DiaryHomeViewModel

    private lateinit var binding: DiaryHomeFragmentLayoutBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.diary_home_fragment_layout, container, false
        )
        val application = requireNotNull(this.activity).application
        val dataSource = DiaryDataBase.getInstance(application).diaryDataBaseDao
        val viewModelFactory = DiaryHomeViewModelFactory(dataSource)
        viewModel =
            ViewModelProvider(requireActivity(), viewModelFactory)[DiaryHomeViewModel::class.java]

        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.diary_home_fragment_title)

        binding.addNewEntryButton.setOnClickListener { view: View ->
            val action =
                DiaryHomeFragmentDirections.actionDiaryHomeFragmentToEntryCreationFragment()
            view.findNavController().navigate(action)
        }

        binding.clearButton.setOnClickListener { view: View ->
            val alert = context?.let { AlertDialog.Builder(it) }
            alert?.let {
                alert.setMessage("Are you really want to delete all entries?")
                alert.setNegativeButton("No", null)
                alert.setPositiveButton("Yes",
                    DialogInterface.OnClickListener { _, _ ->
                        viewModel.clearEntries()
                        Toast.makeText(context, "All entries are cleared", Toast.LENGTH_LONG).show()
                    })
                alert.show()
            }

        }

        val adapter = DiaryEntryAdapter(DiaryItemViewListener { id ->
            Log.i("DiaryHomeFragment", "itemViewModel clicked $id")
            val action = DiaryHomeFragmentDirections.actionDiaryHomeFragmentToEntryEditFragment(id)
            this.findNavController().navigate(action)
        })
        val manager = LinearLayoutManager(activity)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = manager
        viewModel.items.observe(viewLifecycleOwner, Observer {
            //работает 2 раза, по документации так, но по-моему херня какая-то
//            https://stackoverflow.com/questions/50236778/why-livedata-observer-is-being-triggered-twice-for-a-newly-attached-observer
            Log.i("DiaryHomeFragment", "do smth")
            it?.let {
                adapter.submitList(it)
            }
        })

        return binding.root
    }

}