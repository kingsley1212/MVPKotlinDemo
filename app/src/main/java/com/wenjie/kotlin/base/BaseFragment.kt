package com.wenjie.kotlin.base

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseFragment:Fragment() {
    var mainView: View? = null

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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseInitialization()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //   return super.onCreateView(inflater, container, savedInstanceState);
        super.onCreateView(inflater, container, savedInstanceState)
        if (mainView == null) {
            mainView = inflater.inflate(setR_layout(), container, false)
        }
        initializeView()
        return mainView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        doBusiness()
    }
    override fun onDetach() {
        super.onDetach()
        mainView = null
    }

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
        val intent = Intent(activity, clz)
        if (ex != null) intent.putExtras(ex)
        startActivity(intent)
        if (isCloseCurrentActivity) {
            activity!!.finish()
        }
    }
}