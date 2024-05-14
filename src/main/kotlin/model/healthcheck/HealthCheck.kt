package com.bookstore.application.model.healthcheck

import kotlinx.serialization.Serializable

@Serializable
data class HealthCheck(val status: String)
