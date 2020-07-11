package com.example.androiddemosecurecheckcashing.repository.network

import com.example.androiddemosecurecheckcashing.base.BaseResponse
import com.example.androiddemosecurecheckcashing.repository.models.requests.LoginRequest
import com.example.androiddemosecurecheckcashing.repository.models.responses.LoginResponse
import com.example.androiddemosecurecheckcashing.utils.Constants
import com.google.gson.GsonBuilder
import com.orhanobut.hawk.Hawk
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitNetworkService {
    private var mRetrofit: Retrofit? = null
    private var mRetrofitApi: IRetrofitInterface

    init {
        mRetrofitApi = getApiService()
    }

    companion object {
        private var instance: RetrofitNetworkService? = null

        fun getRetrofitServiceInstance(): RetrofitNetworkService {
            if (instance == null) {
                instance = RetrofitNetworkService()
            }
            return instance as RetrofitNetworkService
        }
    }

    private fun getApiService(): IRetrofitInterface {
        if (mRetrofit == null) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor { chain ->
                    val requestBuilder = chain.request().newBuilder()
                    requestBuilder.header("Content-Type", "application/json")
                    val accessToken = Hawk.get(Constants.ACCESS_TOKEN, "")
                    if (accessToken.isNullOrBlank()) {
                        requestBuilder.addHeader("Authorization", accessToken)
                    }
                    chain.proceed(requestBuilder.build())
                }
                .connectTimeout(Constants.DURATION_API_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Constants.DURATION_API_TIMEOUT, TimeUnit.SECONDS)
                .build()

            mRetrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()

        }
        return mRetrofit!!.create(IRetrofitInterface::class.java)
    }

    suspend fun userLogin(loginRequest: LoginRequest): Response<BaseResponse<LoginResponse>> {
        return mRetrofitApi.userLogin(loginRequest)
    }

    fun decideSuccessFailure(response: Response<BaseResponse<Any>>): Any {
        when {
            response.code() != 200 -> return "Something went wrong. Please try again"
            else -> {
                if (response.body()?.HttpStatus   == 200) return response
                else return response.body()!!.Message[0]
            }
        }
    }
}