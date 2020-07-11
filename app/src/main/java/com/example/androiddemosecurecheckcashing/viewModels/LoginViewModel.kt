package com.example.androiddemosecurecheckcashing.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.androiddemosecurecheckcashing.base.BaseResponse
import com.example.androiddemosecurecheckcashing.base.BaseViewModel
import com.example.androiddemosecurecheckcashing.repository.models.requests.LoginRequest
import com.example.androiddemosecurecheckcashing.repository.network.RetrofitNetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.lang.Exception
import java.net.UnknownHostException

class LoginViewModel(var loginViewFields: LoginViewFields) : BaseViewModel() {
//    val userName: ObservableField<String> = ObservableField()
//    val password: ObservableField<String> = ObservableField()
    val loginLiveData = MutableLiveData<Any>()

    fun onLoginBtnClick() {
        viewModelScope.launch {
            try {
                val retrofitService = RetrofitNetworkService.getRetrofitServiceInstance()
                val response = withContext(Dispatchers.IO) {
                   retrofitService.userLogin(LoginRequest(loginViewFields.userName, loginViewFields.Password))
                }
                loginLiveData.value =retrofitService.decideSuccessFailure(response as Response<BaseResponse<Any>>)
            } catch (e: UnknownHostException) {
                loginLiveData.value = "No Internet Connection available."
            } catch (e: Exception) {
                loginLiveData.value = e.toString()
            }
        }
    }
}