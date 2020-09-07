package com.wenjie.kotlin.base

import android.content.Context
import com.wenjie.kotlin.request.Api
import com.wenjie.kotlin.request.ErrorConsumer
import com.wenjie.kotlin.request.OkHttpUtils
import com.wenjie.kotlin.utils.NetUtil
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import java.lang.ref.WeakReference

/**
 * Description:
 */
open class BaseMvpPresenter<V : IBaseMvpView> constructor(context: Context) : IClear {
    protected var mView: V? = null
    protected var mService: Api? = null
    protected val RETRY_TIMES: Long = 0
    var context: Context? = null
    protected final var mDisposables = CompositeDisposable()
    private var weakReferenceView: WeakReference<V>? = null


    init {
        mService = OkHttpUtils.create()
        this.context = context

    }

    fun attachMvpView(view: V) {
        weakReferenceView = WeakReference(view)
        this.mView = weakReferenceView!!.get()
    }

    fun detachMvpView() {
        weakReferenceView!!.clear()
        weakReferenceView = null
        mView = null
    }

    protected open fun add(disposable: Disposable?) {
        mDisposables.add(disposable!!)
    }

    protected open fun getTransformer(): ObservableTransformer<Any?, Any?>? {
        return ObservableTransformer<Any?, Any?> { observable ->
            observable.retry(RETRY_TIMES)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .unsubscribeOn(Schedulers.io())
                    .doOnSubscribe(Consumer<Disposable> { disposable ->
                        if (!NetUtil.isConnected(context!!)) {
                            disposable.dispose()
                            mView!!.onError(2000, "网络连接异常,请检查网络")
                            mView!!.hideLoading()
                        }
                    })
                    .doOnError(ErrorConsumer(mView!!))
                    .doOnComplete(Action { mView!!.hideLoading() })
        }
    }

    override fun clear() {
//        mDisposables.clear()
    }

}