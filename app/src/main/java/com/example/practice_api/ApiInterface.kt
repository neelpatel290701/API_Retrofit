package com.example.practice_api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {

    @GET("posts")
    fun getdata() : Call<List<responseDataItem>>

    @POST("posts")
    fun sendData(
        @Body userData : responseDataItem
    ) : Call<responseDataItem>

}
