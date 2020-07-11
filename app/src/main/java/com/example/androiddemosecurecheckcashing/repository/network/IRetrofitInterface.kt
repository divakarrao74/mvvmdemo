package com.example.androiddemosecurecheckcashing.repository.network

import com.example.androiddemosecurecheckcashing.base.BaseResponse
import com.example.androiddemosecurecheckcashing.repository.models.requests.LoginRequest
import com.example.androiddemosecurecheckcashing.repository.models.responses.LoginResponse
import com.example.androiddemosecurecheckcashing.utils.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface IRetrofitInterface {
    @POST(Constants.URL_USER_LOGIN)
    suspend fun userLogin(@Body loginRequest: LoginRequest):  Response<BaseResponse<LoginResponse>>
}