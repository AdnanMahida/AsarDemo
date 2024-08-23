package com.ad.asardemo.model

data class Status(
    val id: Int,
    val title: String,
    val type: StatusType,
    val iconResId: Int
)