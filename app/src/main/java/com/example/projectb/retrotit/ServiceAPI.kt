package com.example.projectb.retrotit

import com.example.projectb.body.bodyActivity.BodyPost
import com.example.projectb.body.bodyoperator.BodyLogin
import com.example.projectb.body.bodyoperator.BodyRegisterOperator
import com.example.projectb.model.ResponseOperator
import com.example.projectb.model.ResponsePost
import com.example.projectb.model.ResponseUploadImage
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ServiceAPI {
    @POST("/registerOperator")
    fun registerOperator(@Body  body: BodyRegisterOperator): Observable<ResponseUploadImage>

    @POST("/loginOPT")
    fun loginOPT(@Body  body: BodyLogin): Observable<List<ResponseOperator>>

    @POST("/Post")
    fun Post(@Body  body: BodyPost): Observable<ResponsePost>

    @GET("ShowPost")
    fun ShowPost():Observable<List<ResponsePost>>
}