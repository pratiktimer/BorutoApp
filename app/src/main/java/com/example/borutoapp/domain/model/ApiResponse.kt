package com.example.borutoapp.domain.model

import kotlinx.serialization.Serializable

//@Serializable
//data class ApiResponse(
//    val success: Boolean,
//    val message: String? = null,
//    val prevPage: Int? = null,
//    val nextPage: Int? = null,
//    val data: List<Hero> = emptyList(),
//    val lastUpdated: Long? = null
//)
@Serializable
data class ApiResponse<T>(
    val success: Boolean,
    val message: String? = null,
    val prevPage: Int? = null,
    val nextPage: Int? = null,
    val data: List<T> = emptyList(),
    val lastUpdated: Long? = null
)