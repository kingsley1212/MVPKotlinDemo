package com.wenjie.kotlin.module.login.view

import com.wenjie.kotlin.base.IBaseMvpView
import com.wenjie.kotlin.entity.TopicsAndNews
import com.wenjie.kotlin.entity.UserDetail

/**
 * Description:
 */
interface ILoginView : IBaseMvpView {



    fun getUserName(): String

    fun getPassword(): String

    fun showMsg(msg: String)

    fun setText(result: String)

    /**
     * 设置用户信息
     */
    fun setUserDetail(userDetail: UserDetail)

    fun setBebingData(topicsAndNews: TopicsAndNews)
}