package com.example.androiddemosecurecheckcashing.repository.network

interface IApiResponseHandler {
    fun onSuccess(response: Any)
    fun onFailure(errorMsg: String)
}