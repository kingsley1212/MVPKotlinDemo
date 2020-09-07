package com.wenjie.kotlin.entity

import java.io.Serializable

/**
 * Description:
 */
class User : Serializable {
    var id: Int = 0             // 唯一 id
    var login: String? = null       // 登录用户名
    var name: String? = null        // 昵称
    var avatar_url: String? = null  // 头像链接
}