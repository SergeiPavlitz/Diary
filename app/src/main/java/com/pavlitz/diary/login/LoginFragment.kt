package com.pavlitz.diary.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.pavlitz.diary.R
import com.pavlitz.diary.database.auth.AuthDataBase
import com.pavlitz.diary.databinding.LoginFragmentLayoutBinding

class LoginFragment: Fragment() {

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
        val viewModel = ViewModelProvider(requireActivity(), factory)[LoginViewModel::class.java]
        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.login_fragment_title)






        if (viewModel.isFirstTime()){

        }else{

        }
        return binding.root
    }
}