package com.example.passwordstorer.model

import kotlinx.parcelize.Parcelize

@Parcelize
data class PinModel(
    val pinId: Int,
    val pin: String
) : BaseModel()
