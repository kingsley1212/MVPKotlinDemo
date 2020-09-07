package com.wenjie.kotlin.module.login.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.wenjie.kotlin.R
import com.wenjie.kotlin.base.BaseMvpActivity
import com.wenjie.kotlin.entity.TopicsAndNews
import com.wenjie.kotlin.entity.UserDetail
import com.wenjie.kotlin.module.login.presenter.LoginPresenter
import com.wenjie.kotlin.utils.find
import com.wenjie.kotlin.utils.toast
import kotlinx.android.synthetic.main.act_login.*

class LoginAct : BaseMvpActivity<ILoginView, LoginPresenter>(), ILoginView{



    override fun getUserName(): String {
        return username.text.toString()
    }

    override fun getPassword(): String {
        return password.text.toString()
    }

    override fun showMsg(msg: String) {
        toast(msg)
    }

    override fun setText(result: String) {
//        textView.text = result
    }

    override fun createPresenter(context: Context): LoginPresenter {
        return LoginPresenter(context)
    }

    override fun setUserDetail(userDetail: UserDetail) {
    }

    @SuppressLint("SetTextI18n")
    override fun setBebingData(topicsAndNews: TopicsAndNews) {
    }

    override fun onError(code: Int, msg: String) {
        showMsg(msg)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.login -> presenter!!.getCode("13652617057")
        }
    }

    override fun initializeView() {

        getme.setOnClickListener(this@LoginAct)
        login.setOnClickListener(this@LoginAct)
        btn_hebing.setOnClickListener(this@LoginAct)
    }

    override fun doBusiness() {
    }

    override fun setR_layout(): Int {
        return R.layout.act_login
    }

    override fun baseInitialization() {
    }
}
