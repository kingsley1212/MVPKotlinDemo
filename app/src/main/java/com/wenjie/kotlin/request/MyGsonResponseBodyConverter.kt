package com.wenjie.kotlin.request

import com.alibaba.fastjson.JSONObject
import com.google.gson.Gson
import com.wenjie.kotlin.base.BaseResponse
import okhttp3.ResponseBody
import retrofit2.Converter
import java.io.IOException
import java.lang.reflect.Type

class MyGsonResponseBodyConverter<T> internal constructor(private val gson: Gson, private val type: Type) : Converter<ResponseBody, T> {
    @Throws(IOException::class)
    override fun convert(value: ResponseBody): T {
        var response = value.string()
        return try {
            val jsonObject = JSONObject.parseObject(response)
            if (jsonObject.containsKey("data") && jsonObject.containsKey("code") && jsonObject.containsKey("msg")) {
                gson.fromJson(response, type)
            } else {
                val baseResponse: BaseResponse<T> = BaseResponse<T>()
                baseResponse.code = 1
                baseResponse.data=response as T
                response = gson.toJson(baseResponse)
                gson.fromJson(response, type)
            }
        } finally {
            value.close()
        }
    }

}