package com.wenjie.kotlin.base

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import com.wenjie.kotlin.R

abstract class BaseAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        baseInitialization()
        super.onCreate(savedInstanceState)
        setContentView(setR_layout())
        initializeView()
        doBusiness()
        setBackPress()
    }
    /**
     * 设置布局文件
     */
    abstract fun setR_layout(): Int

    /**
     * 基本初始化工作放在这个方法 如 P类
     */
    abstract fun baseInitialization()
    /**
     * 初始化控件
     */
    protected abstract fun initializeView()

    /**
     * 业务逻辑放在这个方法 如获取网络数据
     */
    abstract fun doBusiness()

    /**
     * 打开一个Activity 默认 不关闭当前activity
     */
    open fun gotoActivity(clz: Class<*>?) {
        gotoActivity(clz, false, null)
    }

    open fun gotoActivity(clz: Class<*>?, isCloseCurrentActivity: Boolean) {
        gotoActivity(clz, isCloseCurrentActivity, null)
    }

    open fun gotoActivity(clz: Class<*>?, isCloseCurrentActivity: Boolean, ex: Bundle?) {
        val intent = Intent(this, clz)
        if (ex != null) intent.putExtras(ex)
        startActivity(intent)
        if (isCloseCurrentActivity) {
            finish()
        }
    }

    //返回监听
    open fun setBackPress() {
        try {
            val backView = findViewById<View>(R.id.leftImg_ly)
            backView.setOnClickListener { finish() }
        } catch (e: java.lang.Exception) {
        }
    }
    //设置title
    open fun setTitleTx(title_tx: String?) {
        try {
            val title = findViewById<TextView>(R.id.title)
            title.text = title_tx
        } catch (e: Exception) {
        }
    }
}