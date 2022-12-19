package com.pavlitz.diary.login

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.pavlitz.diary.R
import com.pavlitz.diary.database.auth.AuthDataBase
import com.pavlitz.diary.databinding.PinConfirmFragmentLayoutBinding

class PinConfirmFragment:Fragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: PinConfirmFragmentLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.pin_confirm_fragment_layout, container, false
        )
        val application = requireNotNull(this.activity).application
        val dataSource = AuthDataBase.getInstance(application).authDataBaseDao
        val factory = LoginViewModelFactory(dataSource)
        viewModel = ViewModelProvider(requireActivity(), factory)[LoginViewModel::class.java]

        binding.pinConfirmPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                if (binding.pinConfirmPassword.text.length == 4) {
                    val b = viewModel.confirm(binding.pinConfirmPassword.text.toString().toInt())
                    if (b){
                        Toast.makeText(context, "Pin created", Toast.LENGTH_LONG).show()
                    } else {
                        val alert = context?.let { AlertDialog.Builder(it) }
                        alert?.let {
                            alert.setMessage("Pin is not confirmed")
                            alert.setPositiveButton("Yes", null)
                            alert.show()
                        }
                    }
                }else{
                    //some functionality
                }
            }
        })

        viewModel.authorization.observe(viewLifecycleOwner, Observer {
            it?.let {
                goToLoginFragment()
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pinConfirmPassword.requestFocus()
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(binding.pinConfirmPassword, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun goToLoginFragment(){
        this.findNavController().navigate(PinConfirmFragmentDirections.actionPinConfirmFragmentToLoginFragment())
    }
}