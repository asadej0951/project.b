package com.example.projectb.presenter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import com.example.projectb.body.bodyActivity.BodyPost
import com.example.projectb.body.bodyoperator.BodyLogin
import com.example.projectb.body.bodyoperator.BodyRegisterOperator
import com.example.projectb.model.ResponseOperator
import com.example.projectb.model.ResponsePost
import com.example.projectb.model.ResponseUploadImage
import com.example.projectb.retrotit.Retrotit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.io.ByteArrayOutputStream
import java.io.File
import java.util.concurrent.TimeUnit

class PreseterOperator { var mDisposable: Disposable? = null

    fun RegisterOperatorPresenterRX(
         o_username:String,
        o_pass:String,
         o_name:String,
        o_last:String,
        o_fname:String,
        o_address:String,
         o_email:String,
         o_tel:String,
         o_ime:File,
         res:(Boolean)-> Unit
    ){
        val encodedImagePic1: String
        var UploadImage = ArrayList<BodyRegisterOperator.Data>()

        if (o_ime.absolutePath != "") {
            val myBitmap = BitmapFactory.decodeFile(o_ime.absolutePath)

            if (myBitmap != null) {
                Log.d("register", myBitmap.toString())
                val byteArrayOutputStream =
                    ByteArrayOutputStream()
                myBitmap.compress(
                    Bitmap.CompressFormat.JPEG,
                    70,
                    byteArrayOutputStream
                )
                val byteArrayImage =
                    byteArrayOutputStream.toByteArray()
                encodedImagePic1 = Base64.encodeToString(
                    byteArrayImage,
                    Base64.DEFAULT
                )
                val uploadData = BodyRegisterOperator.Data(
                    o_username,o_pass,o_name,o_last,o_fname,o_address,o_email,o_tel,o_ime.name,"data:image/jpeg;base64,$encodedImagePic1"
                )
                UploadImage.add(uploadData)

            }
        }
        mDisposable = Retrotit.myAppService.registerOperator(BodyRegisterOperator(UploadImage))
            .subscribeOn(Schedulers.io())
            .timeout(20, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<ResponseUploadImage>(){
                override fun onComplete() {}
                override fun onNext(response: ResponseUploadImage) {
                    if (response.isSuccessful){
                        res.invoke(true)
                        Log.d("message",response.message.toString())
                    }else{
                        res.invoke(false)
                    }
                }
                override fun onError(e: Throwable) {
                    Log.d("message",e.message.toString())
                    res.invoke(false)
                }

            })
    }
    fun LoginPresenterRX(
        u_user:String,
        u_pass:String,
        dataResponse:(List<ResponseOperator>)->Unit,
        MessageError:(String)->Unit
    ){
        mDisposable = Retrotit.myAppService.loginOPT(BodyLogin(u_user,u_pass))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<List<ResponseOperator>>(){
                override fun onComplete() {}
                override fun onNext(responseLogin: List<ResponseOperator>) {
                    Log.d("message",responseLogin.toString())
                    dataResponse.invoke(responseLogin)
                }
                override fun onError(e: Throwable) {
                    MessageError.invoke(e.message!!)
                    Log.d("message",e.message.toString())
                }

            })
    }
    fun PostPresenterRX(
        u_id:String,
        p_messssage:String,
        u_name:String,
        p_time:String,
        dataResponse:(ResponsePost)->Unit,
        MessageError:(String)->Unit
    ){
        mDisposable = Retrotit.myAppService.Post(BodyPost(u_id,p_messssage,u_name, p_time))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<ResponsePost>(){
                override fun onComplete() {}
                override fun onNext(responseLogin: ResponsePost) {
                    Log.d("message",responseLogin.toString())
                    dataResponse.invoke(responseLogin)
                }
                override fun onError(e: Throwable) {
                    MessageError.invoke(e.message!!)
                    Log.d("message",e.message.toString())
                }

            })
    }
    fun ShowPostPresenterRX(
        dataResponse:(List<ResponsePost>)->Unit,
        MessageError:(String)->Unit
    ){
        mDisposable = Retrotit.myAppService.ShowPost()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<List<ResponsePost>>(){
                override fun onComplete() {}
                override fun onNext(response: List<ResponsePost>) {
                    Log.d("message",response.toString())
                    dataResponse.invoke(response)
                }
                override fun onError(e: Throwable) {
                    MessageError.invoke(e.message!!)
                    Log.d("message",e.message.toString())
                }

            })
    }
}