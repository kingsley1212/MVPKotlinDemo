package com.wenjie.kotlin.request

import com.wenjie.kotlin.base.BaseResponse
import com.wenjie.kotlin.base.IBaseMvpView
import io.reactivex.functions.Consumer

abstract class SuccessConsumer<T>(private val mBaseView: IBaseMvpView) : Consumer<T> {
    override fun accept(response: T) {
        val baseResponse = response as BaseResponse<*>
        val code = baseResponse.code
        if (code == ResponseCode.SUCCESS) {
            onSuccess(response)
        } else {
            mBaseView.onError(code, baseResponse.msg!!)
        }
    }

    abstract fun onSuccess(response: T)

}