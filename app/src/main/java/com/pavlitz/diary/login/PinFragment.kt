package com.pavlitz.diary.login

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.pavlitz.diary.R
import com.pavlitz.diary.database.auth.AuthDataBase
import com.pavlitz.diary.databinding.PinFragmentLayoutBinding


class PinFragment:Fragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: PinFragmentLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.pin_fragment_layout, container, false
        )
        val application = requireNotNull(this.activity).application
        val dataSource = AuthDataBase.getInstance(application).authDataBaseDao
        val factory = LoginViewModelFactory(dataSource)
        viewModel = ViewModelProvider(requireActivity(), factory)[LoginViewModel::class.java]
        binding.pinFragmentPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                if (binding.pinFragmentPassword.text.length == 4) {
                    viewModel.setPin(binding.pinFragmentPassword.text.toString().toInt())
                    binding.pinFragmentPassword.text.clear()
                    goToPinConfirmFragment()
                }else{
                    //some functionality
                }
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pinFragmentPassword.requestFocus()
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(binding.pinFragmentPassword, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun goToPinConfirmFragment(){
        val action = PinFragmentDirections.actionPinFragmentToPinConfirmFragment()
        this.findNavController().navigate(action)
    }
}