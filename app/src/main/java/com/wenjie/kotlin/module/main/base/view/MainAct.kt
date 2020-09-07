package com.wenjie.kotlin.module.main.base.view

import android.content.Context
import android.graphics.Color
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import com.blankj.utilcode.util.BarUtils
import com.wenjie.kotlin.R
import com.wenjie.kotlin.base.BaseMvpActivity
import com.wenjie.kotlin.module.main.base.presenter.MineFragPresenter
import java.util.*

class MainAct : BaseMvpActivity<IMineFragView, MineFragPresenter>(), IMineFragView {
    //    var rgMainTab: RadioGroup? = null
    private lateinit var rgMainTab: RadioGroup
    private lateinit var rbMainTabHome: RadioButton
    private lateinit var rbMainTabJinHuoDan: RadioButton
    private val resId = intArrayOf(R.id.rbMainTabHome, R.id.rbMainTabJinHuoDan)

    private var fragments: ArrayList<Fragment>? = null
    private var position = 0
    private var ft: FragmentTransaction? = null
    private var CURRENT_FRAGMENT: Fragment? = null
    private val exitTime: Long = 0

    override fun createPresenter(context: Context): MineFragPresenter {
        return MineFragPresenter(this)
    }

    override fun onError(code: Int, msg: String) {
    }

    override fun onClick(v: View?) {
    }

    override fun initializeView() {
        rgMainTab = findViewById(R.id.rgMainTab)
        rbMainTabHome = findViewById(R.id.rbMainTabHome)
        rbMainTabJinHuoDan = findViewById(R.id.rbMainTabJinHuoDan)
        rbMainTabHome.setOnClickListener{switchFragment(0)}
        rbMainTabJinHuoDan.setOnClickListener{switchFragment(1)}
        initEvent()
    }

    private fun initEvent() {
        fragments = ArrayList()
        fragments!!.add(0, HomeFrag())
        fragments!!.add(1, MineFrag())
        rgMainTab.check(resId[position])
        switchFragment(0)
    }

    private fun switchFragment(pos: Int) {
        position = pos
        rgMainTab.check(resId[position])
        ft = supportFragmentManager.beginTransaction()
        if (CURRENT_FRAGMENT != null) {
            ft!!.hide(CURRENT_FRAGMENT!!)
        }
        var fragment = supportFragmentManager.findFragmentByTag(fragments!![pos].javaClass.name)
        if (fragment == null) {
            fragment = fragments!![pos]
        }
        CURRENT_FRAGMENT = fragment
        if (!fragment.isAdded) {
            ft!!.add(R.id.fragment_container, fragment, fragment.javaClass.name)
        } else {
            ft!!.show(fragment)
        }
        ft!!.commitAllowingStateLoss()
    }

    override fun doBusiness() {
    }

    override fun setR_layout(): Int {
        return R.layout.activity_main
    }

    override fun baseInitialization() {
        BarUtils.setStatusBarColor(this,Color.parseColor("#ffffff"))
        BarUtils.setStatusBarLightMode(this, true)
    }
}
