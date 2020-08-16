package com.example.projectb.retrotit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object Retrotit {
    private var retrofit: Retrofit? = null
    private var httpClient : OkHttpClient.Builder? = null
    private var BasUrl = "http://192.168.1.9:4000"

    private fun  getInstance(): Retrofit {


        if (retrofit == null){

            // okhttp เพื่อเชื่อต่อกับสิ่งที่เป็น HTTP
            httpClient = OkHttpClient.Builder()
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)

            //เรียกใช้ retrofit + rxjava2 เพื่อ เชื่อต่อ API
            retrofit = Retrofit.Builder()
                .baseUrl(BasUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient!!.build())
                .build()
        }
        return  retrofit as Retrofit
    }
    val mHttpRetrofit = getInstance()

    //เชื่อมต่อ retrofit และ serviceAPI
    val myAppService = mHttpRetrofit.create(ServiceAPI::class.java)
}