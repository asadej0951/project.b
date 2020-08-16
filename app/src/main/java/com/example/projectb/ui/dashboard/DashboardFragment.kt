package com.example.projectb.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.projectb.R
import com.example.projectb.retrotit.Preferrences
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_dashboard.view.*

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    lateinit var mPreferrences: Preferrences
    private var CODE = 999

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        initviewShow(root)
        initview(root)
        return root.rootView
    }

    private fun initview(root: View) {
        root.btn_addDataShop.setOnClickListener {
            startActivityForResult(
                Intent(context,DataShop_Activity::class.java),CODE
            )
        }
    }

    private fun initviewShow(root: View) {
        mPreferrences   = Preferrences(root.context!!)
        root.nameShop_Shop.setText("ร้าน: "+mPreferrences.getusername())
    }
}