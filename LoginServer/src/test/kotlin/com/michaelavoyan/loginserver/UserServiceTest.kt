/**
 * Created by Michael Avoyan on 20/11/2024.
 *
 * Copyright 2022 Velocity Career Labs inc.
 * SPDX-License-Identifier: Apache-2.0
 */

package com.michaelavoyan.loginserver

import com.michaelavoyan.loginserver.services.UserService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserServiceTest(@Autowired val userService: UserService) {

    @Test
    fun `test user creation and authentication`() {
        val user = userService.createUser("testUser", "testPassword", "test@example.com")
        assertNotNull(user.id)
        assertTrue(userService.authenticate("testUser", "testPassword"))
    }
}
