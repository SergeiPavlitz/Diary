package com.pavlitz.diary.login

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.pavlitz.diary.R
import com.pavlitz.diary.database.auth.AuthDataBase
import com.pavlitz.diary.databinding.LoginFragmentLayoutBinding

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: LoginFragmentLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.login_fragment_layout, container, false
        )
        val application = requireNotNull(this.activity).application
        val dataSource = AuthDataBase.getInstance(application).authDataBaseDao
        val factory = LoginViewModelFactory(dataSource)
        viewModel = ViewModelProvider(requireActivity(), factory)[LoginViewModel::class.java]

        binding.loginCreatePinButton.setOnClickListener { goToPinFragment() }

        binding.loginPasswordField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                if (binding.loginPasswordField.text.length == 4) {
                    val entered = binding.loginPasswordField.text.toString().toInt()
                    if (viewModel.authorize(entered)) {
                        //go to home frag
                        binding.loginPasswordField.text.clear()
                        goToHomeFragment()
                    } else {
                        val alert = context?.let { AlertDialog.Builder(it) }
                        alert?.let {
                            alert.setMessage("Wrong pin")
                            alert.setPositiveButton("Yes", null)
                            alert.show()
                        }
                    }
                }
            }
        })

        viewModel.authorization.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                binding.loginCreatePinButton.visibility = View.GONE
                binding.loginPasswordField.requestFocus()
                val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(binding.loginPasswordField, InputMethodManager.SHOW_IMPLICIT)
            }else{
                goToPinFragment()
            }
        })

        return binding.root
    }

    private fun goToPinFragment() {
        val action = LoginFragmentDirections.actionLoginFragmentToPinFragment()
        this.findNavController().navigate(action)
    }

    private fun goToHomeFragment(){
        val action = LoginFragmentDirections.actionLoginFragmentToDiaryHomeFragment()
        this.findNavController().navigate(action)
    }

}