package com.example.userservice

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.function.Function


data class User(var userid: String?, var name: String = "name")
data class Profile(var profileId: String?, var name: String?)


fun main() {
    val userIdsFlux = Flux.just("user1", "user2", "user3")
    val userIdsMono = Mono.just("user1")
    val res = userIdsFlux.flatMap {
        getUserDetails(it)
    }
//        .subscribe {
//        println(it)
//    }

    val res2 = userIdsMono.flatMap {
        getUserDetails(it)
    }
//        .subscribe {
//        println(it)
//    }


    // 使用flatMap连接这两个操作
    var userProfile = findUserById("123")
        .flatMap { user: User ->
            getUserProfile(
                user
            )
        }

   val  getUserProfile2 = findUserById("123").map {
        getUserProfile(it)
    }
//       .map {
//        it
//    }
}

fun getUserDetails(userId: String?): Mono<User> {
    // 模拟从数据库或远程服务异步获取用户信息
    return Mono.just<User>(User(userId))
}

fun findUserById(id: String?): Mono<User> {
    // 从数据库或服务中查找用户
    return Mono.just(User(id, "UserName"))
}

fun getUserProfile(user: User): Mono<Profile?> {
    // 根据用户信息获取详细的用户配置文件
    return Mono.just<Profile?>(Profile(user.userid, "Profile Description"))
}

