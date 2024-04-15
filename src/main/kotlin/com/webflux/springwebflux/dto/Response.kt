package com.webflux.springwebflux.dto

import java.util.Date

// 每个 Response 实例被转换为一个事件，但如果 Response 的属性是私有的，并且没有提供公共的 getter 方法，序列化机制将无法访问这些属性的值，导致返回的数据为空。
// 所以这里的属性不能是private, 因为你想被序列化看到
data class Response(val date: Date = Date(),  val output:Int)
