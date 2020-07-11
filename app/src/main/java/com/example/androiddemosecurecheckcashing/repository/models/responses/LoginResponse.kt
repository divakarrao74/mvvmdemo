package com.example.androiddemosecurecheckcashing.repository.models.responses

//data class LoginResponse(
//    val Data: Data,
//    val HttpStatus: Int,
//    val Message: List<String>,
//    val Successfull: Boolean
//)

data class LoginResponse(
    val ID: String,
    val IsHosted: Boolean,
    val StationIDS: String,
    val Token: String,
    val Username: String,
    val active: Boolean
)