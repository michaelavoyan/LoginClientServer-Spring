/**
 * Created by Michael Avoyan on 19/11/2024.
 *
 * Copyright 2022 Velocity Career Labs inc.
 * SPDX-License-Identifier: Apache-2.0
 */

package com.michaelavoyan.loginserver

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleException(ex: Exception): String = ex.message ?: "An error occurred"
}
