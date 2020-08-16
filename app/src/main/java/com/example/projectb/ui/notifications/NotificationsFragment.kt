package com.example.projectb.ui.notifications

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.projectb.R
import com.example.projectb.retrotit.Preferrences
import com.example.projectb.ui.login.Loginactivity
import kotlinx.android.synthetic.main.fragment_notifications.view.*

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel
    lateinit var mPreferrences: Preferrences

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        initview(root)
        return root.rootView
    }

    private fun initview(root: View) {
        mPreferrences =  Preferrences(root.context)
        root.login.setOnClickListener {
            mPreferrences.clear()
            startActivity(
                Intent(
                    context,Loginactivity::class.java
                )
            )
            (context as Activity).finish()
        }
    }
}