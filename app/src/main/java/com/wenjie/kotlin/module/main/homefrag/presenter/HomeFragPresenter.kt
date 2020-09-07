package com.wenjie.kotlin.module.main.base.presenter

import android.content.Context
import com.google.gson.Gson
import com.wenjie.kotlin.base.BaseMvpPresenter
import com.wenjie.kotlin.base.BaseResponse
import com.wenjie.kotlin.module.main.base.view.IMineFragView
import com.wenjie.kotlin.request.SuccessConsumer
import io.reactivex.functions.Consumer

/**
 * Description:
 */
class HomeFragPresenter constructor(mContext: Context) : BaseMvpPresenter<IMineFragView>(context = mContext), IMineFragModel {
    var content: Context? = null

    init {
        this.content = mContext
    }

    override fun getCode(phone: String?) {
        add(mService!!.getVerifyCode(phone, "android")!!
                .compose(getTransformer())
                .subscribe(object : SuccessConsumer<Any?>(mView!!) {
                    override fun onSuccess(response: Any?) {
                        var  res=response as BaseResponse<Any?>
                        var gson = Gson()
                        var myJson=gson.toJson(res.data)
                        mView!!.onError(1, res.msg!!)
                    }
                }, Consumer<Throwable> { throwable -> throwable.printStackTrace() }))
    }


}

