package com.wenjie.kotlin.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import com.wenjie.kotlin.utils.DialogUtils

/**
 */
abstract class BaseMvpActivity<V : IBaseMvpView, P : BaseMvpPresenter<V>> : BaseAct(), IBaseMvpView , View.OnClickListener{
    var mContent: Context? = null
    private lateinit var dialog: Dialog
    protected var presenter: P? = null

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContent = this

        when (presenter) {
            null -> presenter = createPresenter(this)
        }
        presenter!!.attachMvpView(this@BaseMvpActivity as V)
    }


    /**
     * 初始化控件
     */
    protected abstract fun createPresenter(context: Context): P

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