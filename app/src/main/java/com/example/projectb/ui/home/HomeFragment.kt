package com.example.projectb.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectb.R
import com.example.projectb.adapter.AdapterPost
import com.example.projectb.model.ResponsePost
import com.example.projectb.presenter.PreseterOperator
import com.example.projectb.retrotit.Preferrences
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.dialog_post.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    lateinit var mPreferrences: Preferrences
    var mPresenterOPT  = PreseterOperator()
    var mResponsePost = ArrayList<ResponsePost>()
    lateinit var mAdapterPost : AdapterPost

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        setAPIShow()
        initView(root)
        return root.rootView
    }

    private fun initView(root: View) {

        mPreferrences = Preferrences(root.context)
        root.tv_username_Post.setText(mPreferrences.getName_lname())
        root.ID_user_Home.setText(mPreferrences.getusername())
        Picasso.get().load("http://192.168.1.9:4000/image/"+mPreferrences.getImage()).into(root.img_user_Post)
        Log.d("message","http://192.168.1.9:4000/image/"+mPreferrences.getImage())
        root.btn_post.setOnClickListener {
            showDialog()
        }

    }

    private fun setAPIShow() {
        mPresenterOPT.ShowPostPresenterRX(this::showPostNext,this::showPostError)
    }

    private fun showPostNext(response: List<ResponsePost>) {
        for (i  in response.indices){
            mResponsePost.add(response[i])
        }
        mAdapterPost = AdapterPost(requireContext(),mResponsePost)
        recycler_post.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapterPost
            mAdapterPost.notifyDataSetChanged()
        }
    }

    private fun showPostError(message: String) {
    }

    private fun showDialog() {
        val mDialogView = layoutInflater.inflate(R.layout.dialog_post,null)
        val message = mDialogView.findViewById<EditText>(R.id.ET_messagePost)
        val Dialog= AlertDialog.Builder(context)
        mDialogView.tv_dialog_post.setText(mPreferrences.getusername())
        Picasso.get().load("http://192.168.1.9:4000/image/"+mPreferrences.getImage()).into(mDialogView.Img_Dialog)
        Dialog.setTitle("โพสต์ข้อความ")
        Dialog.setView(mDialogView)
        Dialog.setPositiveButton("โพสต์"){dialog, which ->
            setAPIPost(message.text.toString())
            mResponsePost.clear()
        }
        Dialog.setNegativeButton("ยกเลิก"){
                dialog, which ->
        }
        Dialog.show()
    }

    private fun setAPIPost(message: String) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val min = calendar.get(Calendar.MINUTE)
        val p_time = "${day}/${month}/${year} - ${hour}:${min}"
        mPresenterOPT.PostPresenterRX(mPreferrences.getID(),message,mPreferrences.getName_lname(),p_time,this::PostNext,this::PostError)
    }

    private fun PostNext(responsePost: ResponsePost) {
        setAPIShow()
    }

    private fun PostError(message: String) {
        Toast.makeText(context, "เกิดข้อผิดพลาด", Toast.LENGTH_SHORT).show()
    }
}