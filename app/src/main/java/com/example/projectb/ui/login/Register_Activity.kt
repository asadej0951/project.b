package com.example.projectb.ui.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.example.projectb.R
import com.example.projectb.model.ResponseOperator
import com.example.projectb.presenter.PreseterOperator
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_register_.*
import java.io.File
import java.lang.Exception

class Register_Activity : AppCompatActivity() {

    var mPreseterOperator = PreseterOperator()
    private lateinit var imageName : File
    private lateinit var addImage : ImageView
    private lateinit var addImagetoDataBase:String
    private var PICK_IMAGE = 999
    var Image :String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_)
        initViewButton()
    }

    private fun initViewButton() {
        btn_back_tologin.setOnClickListener {
            finish()
        }
        save_register.setOnClickListener {
            setAPI()
        }
        btn_image.setOnClickListener {
            val i = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(i,PICK_IMAGE)
        }
    }

    private fun setAPI() {
        mPreseterOperator.RegisterOperatorPresenterRX(
            username_Register.text.toString(),
            password__Register.text.toString(),
            name_Register.text.toString(),
            lname_Register.text.toString(),
            nameShop_Register.text.toString(),
            add__Register.text.toString(),
            email_Register.text.toString(),
            tel_Register.text.toString(),
            imageName
        ){if (it){
            Toast.makeText(applicationContext, "ลงทะเบียนเสร็จสิ้น", Toast.LENGTH_SHORT).show()
            finish()
        }
            else{
            Toast.makeText(applicationContext, "เกิดข้อผิดพลาด", Toast.LENGTH_SHORT).show()
        }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode==PICK_IMAGE&&resultCode== Activity.RESULT_OK){
            try {
                val pickedImage : Uri = data?.data!!
                val filePath = arrayOf(MediaStore.Images.Media.DATA)
                val cursor: Cursor =
                    this.contentResolver.query(pickedImage, filePath, null, null, null)!!
                cursor.moveToFirst()
                val imagePath: String = cursor.getString(cursor.getColumnIndex(filePath[0]))
                val options = BitmapFactory.Options()
                options.inPreferredConfig = Bitmap.Config.ARGB_8888
                BitmapFactory.decodeFile(imagePath, options)

                imageName = File(imagePath)
                Log.d("As5da1sda",imageName.toString())
                Picasso.get().load(imageName).into(imageRegister)
            }catch (e: Exception){
                e.printStackTrace()
                Log.d("As5da1sda",e.toString())
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}