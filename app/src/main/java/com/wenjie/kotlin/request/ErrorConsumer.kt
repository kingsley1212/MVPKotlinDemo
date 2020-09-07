package com.wenjie.kotlin.request

import com.google.gson.JsonParseException
import com.wenjie.kotlin.base.IBaseMvpView
import io.reactivex.functions.Consumer
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.text.ParseException

class ErrorConsumer(private val mBaseView: IBaseMvpView) : Consumer<Throwable> {
    @Throws(Exception::class)
    override fun accept(e: Throwable) {
        mBaseView.hideLoading()
        e.printStackTrace()
        if (e is SocketTimeoutException
                || e is ConnectException
                || e is HttpException) {
            mBaseView.onError(-1, "网络太糟糕了!")
        } else if (e is JsonParseException
                || e is JSONException
                || e is ParseException) {
            mBaseView.onError(-1, "服务器开小差了!")
        } else {
            mBaseView.onError(-1, "服务器繁忙!!")
        }
    }

}