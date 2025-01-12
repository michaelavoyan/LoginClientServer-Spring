/**
 * Created by Michael Avoyan on 19/11/2024.
 *
 * Copyright 2022 Velocity Career Labs inc.
 * SPDX-License-Identifier: Apache-2.0
 */

package com.michaelavoyan.loginserver.controllers

import com.michaelavoyan.loginserver.entities.User
import com.michaelavoyan.loginserver.requests.UserLoginRequest
import com.michaelavoyan.loginserver.requests.UserRegisterRequest
import com.michaelavoyan.loginserver.requests.UserUpdateRequest
import com.michaelavoyan.loginserver.services.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {

    @PostMapping("/register")
    fun register(
        @RequestBody request: UserRegisterRequest
    ): ResponseEntity<User> {
        val user = userService.createUser(request.username, request.password, request.email)
        println("User created: $user")
        return ResponseEntity.ok(user)
    }

    @PostMapping("/login")
    fun login(
        @RequestBody request: UserLoginRequest
    ): ResponseEntity<String> {
        val authenticated = userService.authenticate(request.username, request.password)
        return if (authenticated) {
            println("Login successful")
            ResponseEntity.ok("Login successful")
        } else {
            println("Invalid credentials")
            ResponseEntity.status(401).body("Invalid credentials")
        }
    }

    @GetMapping("/{id}")
    fun getUser(
        @PathVariable id: Long
    ): ResponseEntity<User> {
        val user = userService.getUser(id)
        println("User found: $user")
        return if (user != null) ResponseEntity.ok(user) else ResponseEntity.notFound().build()
    }

    @PutMapping("/{id}")
    fun updateUser(
        @PathVariable id: Long,
        @RequestBody request: UserUpdateRequest
    ): ResponseEntity<User> {

        println("Received request to update user with id: $id")
        println("Request payload: $request")

        val user = try {
            userService.updateUser(id, request.email)
        } catch (e: Exception) {
            println("Error while updating user: ${e.message}")
            return ResponseEntity.status(500).build()
        }

        return if (user != null) {
            println("User updated successfully: $user")
            ResponseEntity.ok(user)
        } else {
            println("User not found for id: $id")
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteUser(
        @PathVariable id: Long
    ): ResponseEntity<Void> {
        userService.deleteUser(id)
        println("User deleted with id: $id")
        return ResponseEntity.noContent().build()
    }
}
