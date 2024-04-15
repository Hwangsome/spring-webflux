//package com.example.userservice.service
//
//import lombok.SneakyThrows
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.beans.factory.annotation.Value
//import org.springframework.boot.CommandLineRunner
//import org.springframework.core.io.Resource
//import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
//import org.springframework.stereotype.Service
//import org.springframework.util.StreamUtils
//import java.nio.charset.StandardCharsets
//import java.sql.DriverManager.println
//
//@Service
//class DataSetupService @Autowired constructor(private val entityTemplate: R2dbcEntityTemplate) : CommandLineRunner {
//    @Value("classpath:h2/init.sql")
//    private lateinit var initSql: Resource
//
//
//
//    override fun run(vararg args: String?) {
//        val query: String = StreamUtils.copyToString(initSql.inputStream, StandardCharsets.UTF_8)
//        println(query)
//        entityTemplate
//            .databaseClient
//            .sql(query)
//            .then()
//            .subscribe()
//    }
//}