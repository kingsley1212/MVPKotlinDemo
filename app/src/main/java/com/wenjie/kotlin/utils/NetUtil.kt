package com.wenjie.kotlin.utils

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Build
import android.provider.Settings

class NetUtil private constructor() {
    companion object {
        /**
         * 判断网络是否连接
         *
         * @param context
         * @return
         */
        fun isConnected(context: Context): Boolean {
            val result: Boolean
            val cm = context
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = cm.activeNetworkInfo
            result = netInfo != null && netInfo.isConnected
            return result
        }

        /**
         * 判断是否是wifi连接
         */
        fun isWifi(context: Context): Boolean {
            val cm = context
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                    ?: return false
            return cm.activeNetworkInfo.type == ConnectivityManager.TYPE_WIFI
        }

        fun is4G(context: Context): Boolean {
            val cm = context
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                    ?: return false
            return cm.activeNetworkInfo.type == ConnectivityManager.TYPE_MOBILE
        }

        /**
         * 打开网络设置界面
         */
        fun openSetting(activity: Activity) {
            var intent = Intent()
            if (Build.VERSION.SDK_INT > 10) {
                intent = Intent(Settings.ACTION_WIRELESS_SETTINGS)
            } else {
                intent = Intent()
                val component = ComponentName("com.android.settings", "com.android.settings.WirelessSettings")
                intent.component = component
                intent.action = "android.intent.action.VIEW"
            }
            activity.startActivity(intent)
        }
    }

    init {
        throw UnsupportedOperationException("cannot be instantiated")
    }
}