package com.wenjie.kotlin.request

import com.wenjie.kotlin.base.BaseResponse
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {
    /**
     * 获取验证码
     * ispwd
     *
     * @param mobile
     * @param type
     * @return
     */
    @FormUrlEncoded
    @POST("salesman/salesman/salesmanSend")
    fun getVerifyCode(@Field("target") mobile: String?,
                      @Field("device_type") type: String?): Observable<BaseResponse<Any?>?>?

    /**
     * 账号登录
     *
     * @param target
     * @param verification_code
     * @return
     */
    @FormUrlEncoded
    @POST("salesman/salesman/loginByPhone")
    fun login(@Field("target") target: String?,
              @Field("verification_code") verification_code: String?,
              @Field("registration_id") registrationId: String?,
              @Field("device_type") device_type: String?): Observable<BaseResponse<Any?>?>?
}