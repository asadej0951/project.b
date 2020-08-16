package com.example.projectb.body.bodyoperator

import java.io.File

data class BodyRegisterOperator(var data: ArrayList<Data>) {

    class Data(
        o_username:String,
        o_pass:String,
        o_name:String,
        o_last:String,
        o_fname:String,
        o_address:String,
        o_email:String,
        o_tel:String,
        o_ime: String,
        img: String
    ) {

        var o_username= ""
        var o_pass= ""
        var o_name= ""
        var o_last= ""
        var o_fname= ""
        var o_address= ""
        var o_email= ""
        var o_tel= ""
        var o_ime = ""
        var img = ""

        init {
            this.o_username = o_username
            this.o_pass = o_pass
            this.o_name = o_name
            this.o_last = o_last
            this.o_fname = o_fname
            this.o_address = o_address
            this.o_email = o_email
            this.o_tel = o_tel
            this.o_ime = o_ime
            this.img = img
        }
    }

}
