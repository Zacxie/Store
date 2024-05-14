package com.bookstore.application.dto.healthcheck

import kotlinx.serialization.Serializable

@Serializable
data class HealthCheckDto(val status: String)
