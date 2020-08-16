package com.example.projectb.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projectb.MainActivity
import com.example.projectb.R
import com.example.projectb.model.ResponseOperator
import com.example.projectb.presenter.PreseterOperator
import com.example.projectb.retrotit.Preferrences
import kotlinx.android.synthetic.main.activity_loginactivity.*

class Loginactivity : AppCompatActivity() {
    var mPresenterOPT = PreseterOperator()
    var mPreferrences = Preferrences(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginactivity)
        initview()
        initviewButton()

    }

    private fun initview() {
        if (checkIsUsername(mPreferrences.getusername())){
            startActivity(
                Intent(
                    applicationContext,MainActivity::class.java
                )
            )
            finish()
        }
    }

    private fun initviewButton() {
        btn_cancal.setOnClickListener {
            ID_login.text.clear()
            password_login.text.clear()
        }
        btn_toRegister.setOnClickListener {
            startActivity(
                Intent(
                    applicationContext,Register_Activity::class.java
                )
            )
        }
        btn_login.setOnClickListener {
            setAPILogin()
        }
    }

    private fun setAPILogin() {
        mPresenterOPT.LoginPresenterRX(
            ID_login.text.toString(),
            password_login.text.toString(),
            this::LoginNext,this::LoginError
        )
    }

    private fun LoginNext(responseLogin: List<ResponseOperator>) {
        mPreferrences.saveId(responseLogin[0].o_id)
        mPreferrences.saveImage(responseLogin[0].o_ime)
        mPreferrences.saveName_lname("${responseLogin[0].o_name} ${responseLogin[0].o_last}")
        mPreferrences.saveUsername(responseLogin[0].o_fname)
        startActivity(
            Intent(
                applicationContext,MainActivity::class.java
            )
        )
        finish()
    }

    private fun LoginError(message:String) {
    }
    fun checkIsUsername(username:String):Boolean{
        return  username.isNotEmpty()
    }
}