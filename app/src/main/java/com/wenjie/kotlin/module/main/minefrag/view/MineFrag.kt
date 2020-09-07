package com.wenjie.kotlin.module.main.base.view

import android.app.Activity
import android.graphics.Color
import android.support.design.widget.AppBarLayout
import android.support.design.widget.AppBarLayout.OnOffsetChangedListener
import android.support.design.widget.CoordinatorLayout
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.wenjie.kotlin.R
import com.wenjie.kotlin.base.BaseMvpFragment
import com.wenjie.kotlin.module.main.base.presenter.MineFragPresenter


class MineFrag : BaseMvpFragment<IMineFragView, MineFragPresenter>(), IMineFragView {
    private lateinit var mImgZhangdan: ImageView
    private lateinit var mJiahao: ImageView
    private lateinit var mTongxunlu: ImageView
    private lateinit var mImgShaomiao: ImageView
    private lateinit var mImgFukuang: ImageView
    private lateinit var mImgZhaoxiang: ImageView
    private lateinit var mImgSearch: ImageView
    private lateinit var mImgZhangdanTxt: TextView
    private lateinit var toolbar1: View
    private lateinit var toolbar2: View
    private lateinit var mAppBarLayout: AppBarLayout
    private lateinit var mActivityMain: CoordinatorLayout

    override fun onError(code: Int, msg: String) {
    }


    override fun initializeView() {
        mTongxunlu = mainView!!.findViewById<ImageView>(R.id.tongxunlu)
        mImgZhaoxiang = mainView!!.findViewById<ImageView>(R.id.img_zhaoxiang)
        mImgFukuang = mainView!!.findViewById<ImageView>(R.id.img_fukuang)
        mJiahao = mainView!!.findViewById<ImageView>(R.id.img_zhangdan)
        mImgSearch = mainView!!.findViewById<ImageView>(R.id.img_search)
        mImgShaomiao = mainView!!.findViewById<ImageView>(R.id.img_shaomiao)
        mImgZhangdan = mainView!!.findViewById<ImageView>(R.id.jiahao)
        mImgZhangdanTxt = mainView!!.findViewById<TextView>(R.id.img_zhangdan_txt)
        toolbar1 = mainView!!.findViewById<View>(R.id.toolbar1)
        toolbar2 = mainView!!.findViewById<View>(R.id.toolbar2)
        mAppBarLayout = mainView!!.findViewById(R.id.appBarLayout)
        mActivityMain = mainView!!.findViewById(R.id.activity_main)

        mAppBarLayout.addOnOffsetChangedListener(OnOffsetChangedListener { appBarLayout, verticalOffset ->
            //                System.out.println("verticalOffset = [" + verticalOffset + "]" + "{" + Math.abs(verticalOffset) + "}" + "{:" + appBarLayout.getTotalScrollRange() + "}");
            if (verticalOffset == 0) {
                //完全展开
                toolbar1.visibility = View.VISIBLE
                toolbar2.visibility = View.GONE
                setToolbar1Alpha(255)
            } else if (Math.abs(verticalOffset) == appBarLayout.totalScrollRange) {
                //appBarLayout.getTotalScrollRange() == 200
                //完全折叠
                toolbar1.visibility = View.GONE
                toolbar2.visibility = View.VISIBLE
                setToolbar2Alpha(255)
            } else { //0~200上滑下滑
                if (toolbar1.visibility == View.VISIBLE) {
//                        //操作Toolbar1
                    val alpha = 300 - 155 - Math.abs(verticalOffset)
                    setToolbar1Alpha(alpha)
                } else if (toolbar2.visibility == View.VISIBLE) {
                    if (Math.abs(verticalOffset) > 0 && Math.abs(verticalOffset) < 200) {
                        toolbar1.visibility = View.VISIBLE
                        toolbar2.visibility = View.GONE
                        setToolbar1Alpha(255)
                    }
                    //                        //操作Toolbar2
                    val alpha = (255 * (Math.abs(verticalOffset) / 100f)).toInt()
                    setToolbar2Alpha(alpha)
                }
            }
        })
    }
    private fun setToolbar1Alpha(alpha: Int) {
        mImgZhangdan.drawable.alpha = alpha
        mImgZhangdanTxt.setTextColor(Color.argb(alpha, 255, 255, 255))
        mTongxunlu.drawable.alpha = alpha
        mJiahao.drawable.alpha = alpha
    }

    private fun setToolbar2Alpha(alpha: Int) {
        mImgShaomiao.drawable.alpha = alpha
        mImgFukuang.drawable.alpha = alpha
        mImgSearch.drawable.alpha = alpha
        mImgZhaoxiang.drawable.alpha = alpha
    }
    override fun doBusiness() {
    }

    override fun setR_layout(): Int {
        return R.layout.frag_mine
    }

    override fun baseInitialization() {
    }

    override fun onWakeBussiness() {
    }

    override fun createPresenter(context: Activity): MineFragPresenter {
        return MineFragPresenter(context)
    }


}
