package com.example.userservice.service

import com.example.userservice.dto.UserDto
import com.example.userservice.entity.User
import com.example.userservice.repository.UserRepository
import com.example.userservice.util.EntityDtoUtil.toDto
import com.example.userservice.util.EntityDtoUtil.toEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.function.Function


@Service
class UserService @Autowired constructor(private val userRepository: UserRepository) {
    fun all(): Flux<UserDto> =
        userRepository.findAll()
            .map { it.toDto() }


    fun getUserById(userId: Int): Mono<UserDto> =
        userRepository.findById(userId)
            .map { it.toDto() }


    fun createUser(userDtoMono: Mono<UserDto>): Mono<UserDto> =
        userDtoMono
            .map { it.toEntity() }
            .flatMap {
                userRepository.save(it)
            }
            .map { it.toDto() }


    fun updateUser(id: Int, userDtoMono: Mono<UserDto>): Mono<UserDto> =
        userRepository.findById(id)
            .flatMap {
                userDtoMono
                    .map { it.toEntity() }
                    .doOnNext {
                        it.id = id
                    }
            }
            .flatMap {
                userRepository.save(it)
            }
            .map { it.toDto() }




    fun deleteUser(id: Int): Mono<Void> = userRepository.deleteById(id)

}