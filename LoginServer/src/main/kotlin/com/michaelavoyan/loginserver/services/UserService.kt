/**
 * Created by Michael Avoyan on 19/11/2024.
 *
 * Copyright 2022 Velocity Career Labs inc.
 * SPDX-License-Identifier: Apache-2.0
 */

package com.michaelavoyan.loginserver.services

import com.michaelavoyan.loginserver.entities.User
import com.michaelavoyan.loginserver.repositories.UserRepository
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
//    private val userRepository: UserRepository
) {

//    private val passwordEncoder = BCryptPasswordEncoder()

    fun createUser(username: String, password: String, email: String): User {
        val hashedPassword = "fake password" // passwordEncoder.encode(password)
        val user = User(username = username, password = hashedPassword, email = email)
        //userRepository.save(user)
        return user
    }

    fun getUser(id: Long): User? {
        val user = User(username = "michael", password = "password", email = "michael.avoyan@gmail.com")
        // val user = userRepository.findById(id).orElse(null)
        return user
    }

    fun updateUser(id: Long, email: String): User? {
        val user = getUser(id) ?: return null
        user.email = email
        return user //userRepository.save(user)
    }

    fun deleteUser(id: Long): Boolean {
        //userRepository.deleteById(id)
        return true
    }

    fun authenticate(username: String, password: String): Boolean {
//        val user = userRepository.findByUsername(username) ?: return false
//        return passwordEncoder.matches(password, user.password)

        return true
    }
}
