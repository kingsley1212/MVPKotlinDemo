package com.wenjie.kotlin.request

import com.google.gson.GsonBuilder
import com.wenjie.kotlin.base.MvpKotlinApplication
import com.wenjie.kotlin.utils.CacheUtil
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
/**
 * Description:
 */
class OkHttpUtils private constructor() {

    private val cacheUtil: CacheUtil = CacheUtil(MvpKotlinApplication.Companion.instence)
    private var retrofit: Retrofit? = null
    private var okHttpClient: OkHttpClient? = null
    companion object {
        private var instance: OkHttpUtils? = null
        fun create(): Api? {
            if (instance == null) {
                synchronized(OkHttpUtils::class.java) {
                    if (instance == null) {
                        instance = OkHttpUtils()
                    }
                }
            }
            return instance!!.getRetrofit()
        }
    }

    fun getRetrofit(): Api? {
        if (retrofit == null) {
            var gson = GsonBuilder().setLenient()
                    //.serializeNulls() //当字段值为空或null时，依然对该字段进行转换
                    //.setPrettyPrinting() //对结果进行格式化，增加换行
                    //.disableHtmlEscaping() //防止特殊字符出现乱码
                    .create()
            retrofit = Retrofit.Builder()
                    .baseUrl(Url.BASE_URL)
                    .client(getOkHttpClient())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(MyGsonConverterFactory.create(gson))
                    .build()
        }
        return retrofit!!.create(Api::class.java)
    }

    private fun getOkHttpClient(): OkHttpClient? {
        if (okHttpClient == null) {
            val builder = OkHttpClient.Builder()
            builder.addInterceptor(Interceptor { chain ->
                val original = chain.request()
                var requsetBuilder = original.newBuilder()
                // 如果当前没有缓存 token 或者请求已经附带 token 了，就不再添加
//                if (cacheUtil.token == null || alreadyHasAuthorizationHeader(original)) {
                    return@Interceptor chain.proceed(requsetBuilder.build())
//                }
//                val token = "Bearer " + cacheUtil.token.access_token
//                val request = original.newBuilder()
//                        .header("Authorization", token)
//                        .build()
//                return@Interceptor chain.proceed(request)
            })
            builder.connectTimeout(15 * 1000, TimeUnit.SECONDS)
            builder.readTimeout(15 * 1000, TimeUnit.SECONDS)
            okHttpClient = builder.build()
        }
        return okHttpClient
    }

    private fun alreadyHasAuthorizationHeader(originalRequest: Request): Boolean {
        val token = originalRequest.header("Authorization")
        // 如果本身是请求 token 的 URL，直接返回 true
        // 如果不是，则判断 header 中是否已经添加过 Authorization 这个字段，以及是否为空
        return !(token == null || token.isEmpty() || originalRequest.url().toString().contains("https://www.diycode.cc/oauth/token"))
    }


//    private var okHttpClient: OkHttpClient? = null
//
//    private val cacheUtil: CacheUtil = CacheUtil(MvpKotlinApplication.Companion.instence)
//
//    private var retrofit: Retrofit? = null
//
//    fun getRetrofit(): Retrofit? {
//        if (retrofit == null) {
//            retrofit = Retrofit.Builder()
//                    .baseUrl("https://diycode.cc/api/v3/")
//                    .client(getOkHttpClient())
//                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build()
//        }
//        return retrofit
//    }
//
//    private fun getOkHttpClient(): OkHttpClient? {
//        if (okHttpClient == null) {
//            val builder = OkHttpClient.Builder()
//            builder.addInterceptor(Interceptor { chain ->
//                val original = chain.request()
//                // 如果当前没有缓存 token 或者请求已经附带 token 了，就不再添加
//                if (cacheUtil.token == null || alreadyHasAuthorizationHeader(original)) {
//                    return@Interceptor chain.proceed(original)
//                }
//                val token = "Bearer " + cacheUtil.token.access_token
//                val request = original.newBuilder()
//                        .header("Authorization", token)
//                        .build()
//                return@Interceptor chain.proceed(request)
//            })
//            builder.connectTimeout(15 * 1000, TimeUnit.SECONDS)
//            builder.readTimeout(15 * 1000, TimeUnit.SECONDS)
//            okHttpClient = builder.build()
//        }
//        return okHttpClient
//    }
//
//    private fun alreadyHasAuthorizationHeader(originalRequest: Request): Boolean {
//        val token = originalRequest.header("Authorization")
//        // 如果本身是请求 token 的 URL，直接返回 true
//        // 如果不是，则判断 header 中是否已经添加过 Authorization 这个字段，以及是否为空
//        return (token == null || token.isEmpty() || originalRequest.url().toString().contains("https://www.diycode.cc/oauth/token"))
//    }
}

