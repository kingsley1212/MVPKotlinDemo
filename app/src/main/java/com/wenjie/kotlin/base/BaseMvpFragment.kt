package com.wenjie.kotlin.base

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.wenjie.kotlin.utils.DialogUtils

/**
 * Description:
 */
abstract class BaseMvpFragment<V : IBaseMvpView, P : BaseMvpPresenter<V>> : BaseFragment(), IBaseMvpView {
    //是否执行唤醒监听
    var wakeListener = true

    var isHiddens = true
    var isFirstResume = true

    protected var presenter: P? = null
    var mContent: Context? = null
    private lateinit var dialog: Dialog

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        when (presenter) {
            null -> presenter = activity?.let { createPresenter(it) }
        }
        presenter!!.attachMvpView(this@BaseMvpFragment as V)
    }

    //唤醒
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        isHiddens = hidden
        if (presenter == null) {
            return
        }
        if (!hidden && wakeListener) {
            onWakeBussiness()
        }
    }

    override fun onResume() {
        super.onResume()
        if (!isFirstResume && !isHidden && wakeListener) {
            onWakeBussiness()
        }
        isFirstResume = false
    }

    protected abstract fun createPresenter(context: Activity): P
    abstract fun onWakeBussiness()
    override fun onDestroy() {
        super.onDestroy()
        presenter!!.detachMvpView()
    }

    fun showProgressDialog(message: String) {
        if (dialog == null) {

            dialog = DialogUtils.createLoadingDialog(mContent as BaseMvpActivity<V, P>, if (message.isNullOrEmpty()) "加载中..." else message)
        }
    }

    override fun showDialog() {

    }

    override fun hideLoading() {

    }
}