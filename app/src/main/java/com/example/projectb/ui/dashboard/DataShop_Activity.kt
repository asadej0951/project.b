package com.example.projectb.ui.dashboard

import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projectb.R
import com.example.projectb.retrotit.Preferrences
import kotlinx.android.synthetic.main.activity_data_shop_.*
import java.util.*

class DataShop_Activity : AppCompatActivity() {

    lateinit var mPreferrences: Preferrences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_shop_)
        initviewButton()
        initViewShow()
    }

    private fun initViewShow() {
        mPreferrences   = Preferrences(this)
        nameShop_Data.setText(mPreferrences.getusername())
    }

    private fun initviewButton() {
        btn_back_toShop.setOnClickListener {
            finish()
        }
        btn_TimeOpen.setOnClickListener { showDialogTimeOpen() }
        btn_TimeClose.setOnClickListener { showDialogTimeClose() }
    }
    private fun showDialogTimeOpen() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val min = calendar.get(Calendar.MINUTE)
        val listener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            var HOUR =""
            var MIN =""
            if (hourOfDay.toString().length==1){HOUR = "0${hourOfDay}"}
            else{HOUR = "${hourOfDay}"}
            if (minute.toString().length ==1){MIN = "0${minute}"}
            else{MIN = "${minute}" }
            val dateStr = "$HOUR:$MIN"
            Time_Open.setText(dateStr)
        }
        val dialog = TimePickerDialog(this@DataShop_Activity,listener,hour,min,true)
        dialog.show()
    }
    private fun showDialogTimeClose() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val min = calendar.get(Calendar.MINUTE)
        val listener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            var HOUR =""
            var MIN =""
            if (hourOfDay.toString().length==1){HOUR = "0${hourOfDay}"}
            else{HOUR = "${hourOfDay}"}
            if (minute.toString().length ==1){MIN = "0${minute}"}
            else{MIN = "${minute}" }
            val dateStr = "$HOUR:$MIN"
            Time_Close.setText(dateStr)
        }
        val dialog = TimePickerDialog(this@DataShop_Activity,listener,hour,min,true)
        dialog.show()
    }
}