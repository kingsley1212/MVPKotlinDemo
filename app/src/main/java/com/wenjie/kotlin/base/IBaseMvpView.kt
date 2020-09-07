package com.wenjie.kotlin.base

import android.app.Dialog

/**
 * Description:
 */
interface IBaseMvpView{
    fun showDialog()
    fun hideLoading()
    fun onError(code: Int, msg: String)

}