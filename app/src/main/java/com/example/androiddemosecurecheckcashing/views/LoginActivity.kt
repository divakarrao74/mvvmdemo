package com.example.androiddemosecurecheckcashing.views

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.androiddemosecurecheckcashing.R
import com.example.androiddemosecurecheckcashing.base.BaseActivity
import com.example.androiddemosecurecheckcashing.databinding.ActivityLoginBinding
import com.example.androiddemosecurecheckcashing.viewModels.LoginViewFields
import com.example.androiddemosecurecheckcashing.viewModels.LoginViewModel
import com.example.androiddemosecurecheckcashing.viewModels.ViewModelProviderFactory
import com.orhanobut.hawk.Hawk

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {

    private lateinit var mLoginViewModel: LoginViewModel
    private lateinit var mActivityLoginBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityLoginBinding = getViewDataBinding()
        mActivityLoginBinding.loginViewModel = mLoginViewModel
        mActivityLoginBinding.loginViewFields = mLoginViewModel.loginViewFields

        observeLoginLiveData()

        Hawk.init(applicationContext).build()
    }

    private fun observeLoginLiveData() {
        mLoginViewModel.loginLiveData.observe(this, Observer { response ->
            if (response is String) {
                showToastMsg(response)
            } else {
                showToastMsg("success")
            }
        })
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun getViewModel(): LoginViewModel {
        mLoginViewModel = LoginViewModel(LoginViewFields())
        mLoginViewModel = ViewModelProviders.of(this, ViewModelProviderFactory(mLoginViewModel))
            .get(LoginViewModel::class.java)
        return mLoginViewModel
    }

      fun showToastMsg(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}