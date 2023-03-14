package com.lamdapay.CryptoMandalam.domain.model


data class Proposal(
    val id: Int,
    val title: String,
    val description: String,
    val creator: UserModel,
    val votes: List<Vote>,
    val targetFunds: Double,
    val currentFunds: Double,
    val approved: Boolean
)
