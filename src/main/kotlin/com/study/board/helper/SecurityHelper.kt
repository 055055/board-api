package com.study.board.helper

import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder

private val passwordEncoder: PasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()

fun encodePassword(rawPassword: String): String = passwordEncoder.encode(rawPassword)

fun checkAuthorization(originAuthor: String, originPassword: String, requestAuthor: String, requestPassword: String) {
    if ((originAuthor != requestAuthor) ||
        (!passwordEncoder.matches(requestPassword, originPassword))) {
        throw IllegalAccessException("invalid author!")
    }
}
