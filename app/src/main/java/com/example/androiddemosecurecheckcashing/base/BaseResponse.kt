package com.example.androiddemosecurecheckcashing.base

data class BaseResponse<T>(
    val Data: T,
    val HttpStatus: Int,
    val Message: List<String>,
    val Successfull: Boolean
)