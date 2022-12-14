package com.pavlitz.diary.entryCreation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.pavlitz.diary.R
import com.pavlitz.diary.databinding.EntryCreationFragmentLayoutBinding
import java.text.SimpleDateFormat
import java.util.*

class EntryCreationFragment : Fragment() {

    private lateinit var viewModel: EntryCreationViewModel

    private lateinit var binding: EntryCreationFragmentLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.entry_creation_fragment_layout,
            container, false
        )

        Log.i("EntryCreationFragment", "Called ViewModelProvider.get")
        viewModel = ViewModelProvider(this)[EntryCreationViewModel::class.java]

        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.entry_creation_fragment_title)

//        для верси андроида от 26
//        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")
//        val date  = LocalDateTime.now().format(formatter)
        val simpleFormatter = SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault())
        binding.entryCreationDate.text = viewModel.date.value?.let { simpleFormatter.format(it) }

        binding.entryCreationTopic.setOnFocusChangeListener { view, b ->
            if (view != null && !b) {
                val text = binding.entryCreationTopic.text
                if (text.isNotBlank()) {
                    viewModel.setTopic(text.toString())
                }
            }
        }

        binding.entryCreationBody.setOnFocusChangeListener { view, b ->
            if (view != null && !b) {
                val text = binding.entryCreationTopic.text
                if (text.isNotBlank()) {
                    viewModel.setBody(text.toString())
                }
            }
        }

        binding.entryCreationSaveButton.setOnClickListener { view ->

            if (viewModel.isBlanc()) {

                val alert = context?.let { AlertDialog.Builder(it) }


            } else {

            }

        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.entryCreationSaveButton.setOnClickListener { view ->
            if (viewModel.isBlanc()) {
                val alert = context?.let {
                    AlertDialog.Builder(it)
                }
                alert?.let {
                    alert.setTitle("There are empty fields")
                    alert.setMessage("Fill all fields")
                    alert.setPositiveButton("OK", null)
                    alert.show()
                }
            } else {
                val action =
                    EntryCreationFragmentDirections.actionEntryCreationFragmentToDiaryHomeFragment(
                        viewModel.getDate(), viewModel.getTopic(), viewModel.getBody()
                    )
                view.findNavController().navigate(action)
            }
        }
    }
}