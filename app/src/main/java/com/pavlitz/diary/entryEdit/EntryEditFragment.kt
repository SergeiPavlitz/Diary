package com.pavlitz.diary.entryEdit

import android.content.DialogInterface
import android.os.Bundle
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
import androidx.navigation.fragment.navArgs
import com.pavlitz.diary.R
import com.pavlitz.diary.database.diary.DiaryDataBase
import com.pavlitz.diary.databinding.EntryEditFragmentLayoutBinding

class EntryEditFragment : Fragment() {

    private lateinit var viewModel: EntryEditViewModel

    private lateinit var binding: EntryEditFragmentLayoutBinding

    private val args: EntryEditFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.entry_edit_fragment_layout, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = DiaryDataBase.getInstance(application).diaryDataBaseDao
        val factory = EntryEditViewModelFactory(args.diaryEntryId, dataSource)
        viewModel = ViewModelProvider(this, factory)[EntryEditViewModel::class.java]

        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.entry_edit_fragment_title)


        viewModel.entryLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.entryEditTopic.setText(it.topic)
                binding.entryEditMood.setText(it.mood)
                binding.entryEditBody.setText(it.body)
            }
        })

        binding.entryEditModifyButton.setOnClickListener(View.OnClickListener { view ->
            val body = binding.entryEditBody.text
            val mood = binding.entryEditMood.text
            val topic = binding.entryEditTopic.text
            if (body.isNotBlank() && topic.isNotBlank() && mood.isNotBlank()) {
                viewModel.updateEntry(body.toString(),mood.toString(), topic.toString())
                Toast.makeText(context, "Entry updated", Toast.LENGTH_LONG).show()
                val action =
                    EntryEditFragmentDirections.actionEntryEditFragmentToDiaryHomeFragment()
                view.findNavController().navigate(action)
            } else {
                val alert = context?.let { AlertDialog.Builder(it) }
                alert?.let {
                    alert.setMessage("Fields mustn't be empty")
                    alert.setPositiveButton("Yes", null)
                    alert.show()
                }
            }
        })

        binding.entryEditDeleteButton.setOnClickListener(View.OnClickListener { view ->
            val alert = context?.let { AlertDialog.Builder(it) }
            alert?.let {
                alert.setMessage("Are you really want to delete the entry?")
                alert.setNegativeButton("No", null)
                alert.setPositiveButton("Yes",
                    DialogInterface.OnClickListener { _, _ ->
                        viewModel.deleteEntry()
                        Toast.makeText(context, "Entry deleted", Toast.LENGTH_LONG).show()
                        val action =
                            EntryEditFragmentDirections.actionEntryEditFragmentToDiaryHomeFragment()
                        view.findNavController().navigate(action)
                    })
                alert.show()
            }
        })

        return binding.root
    }
}