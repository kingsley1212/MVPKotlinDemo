package com.wenjie.kotlin.base

import android.app.Application

/**
 * Description:
 */
class MvpKotlinApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instence = this
    }

    companion object {
        lateinit var instence: MvpKotlinApplication
            private set
    }

}