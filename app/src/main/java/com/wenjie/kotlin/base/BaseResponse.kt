package com.wenjie.kotlin.base

class BaseResponse<T> {
    var code = 0
    var data: T? = null
    var msg: String? = null
}