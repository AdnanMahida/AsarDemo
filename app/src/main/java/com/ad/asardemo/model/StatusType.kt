package com.ad.asardemo.model

sealed class StatusType
data class Crypto(
    val amount: Double,
    val upAmount: Double
) : StatusType()

data class Sports(
    val description: String
) : StatusType()