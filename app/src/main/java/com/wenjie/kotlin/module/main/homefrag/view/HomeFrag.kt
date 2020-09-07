package com.wenjie.kotlin.module.main.base.view

import android.app.Activity
import com.wenjie.kotlin.R
import com.wenjie.kotlin.base.BaseMvpFragment
import com.wenjie.kotlin.module.main.base.presenter.MineFragPresenter

class HomeFrag : BaseMvpFragment<IMineFragView, MineFragPresenter>(), IMineFragView {



    override fun onError(code: Int, msg: String) {
    }


    override fun initializeView() {
    }

    override fun doBusiness() {
    }

    override fun setR_layout(): Int {
        return R.layout.frag_home
    }

    override fun baseInitialization() {
    }

    override fun onWakeBussiness() {
    }

    override fun createPresenter(context: Activity): MineFragPresenter {
        return MineFragPresenter(context)
    }


}
