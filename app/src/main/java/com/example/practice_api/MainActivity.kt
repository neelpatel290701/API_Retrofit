package com.example.practice_api

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var textView : TextView

    private fun getData() {
        RetrofitInstance.apiInterface.getdata().enqueue(object : Callback<List<responseDataItem>> {
            override fun onResponse(
                call: Call<List<responseDataItem>>,
                response: Response<List<responseDataItem>>
            ) {
                try {

                    if (response.isSuccessful) {
                        val post = response.body()
                        // Handle the retrieved post data
                        Log.d("Success", "Ok")
                        Log.d("Success" ,"response body $post")

                    } else {
                        // Handle error
                        Log.d("Success", "No")
                    }

                } catch (e: Exception) {
                    Log.e("MainActivity GET", "Error: ${e.message}", e)
                }
            }

            override fun onFailure(call: Call<List<responseDataItem>>, t: Throwable) {

                Log.d("MainActivity GET", "onFailure")
//                    if (t is HttpException) {
                Log.d("MainActivity GET", "HTTP Status Code: $t")
//                    }
            }

        })

    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.tv)

        textView.text = "NEEL"

//        getData()

        val userData = responseDataItem("neel",7,"ZenAPI",1)

        RetrofitInstance.apiInterface.sendData(userData).enqueue(object : Callback<responseDataItem?> {
            @SuppressLint("SuspiciousIndentation")
            override fun onResponse(
                call: Call<responseDataItem?>,
                response: Response<responseDataItem?>
            ) {

                try {
                    textView.text = response.code().toString()
                      Log.d("MainActivity POST" ,"success $response")

                }catch (e: Exception) {
                    Log.e("MainActivity POST", "Error: ${e.message}", e)
                }
            }

            override fun onFailure(call: Call<responseDataItem?>, t: Throwable) {

                Log.d("MainActivity POST", "onFailure")
                    if (t is HttpException) {
                Log.d("MainActivity POST", "HTTP Status Code: $t")
                    }
            }
        })

    }

}