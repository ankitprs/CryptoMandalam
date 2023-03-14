package com.lamdapay.CryptoMandalam.domain.model

data class FundModel(
    val name: String = "",
    val description: String= "",
    val imageUrl: String= "",
    val pledgedAmount: Double,
    val fundingGoal: Double,
    val progress: Float,
    val daoName: String= "",
    val crowdfundingTiming: String= "",
    val fundCategory: String= "",
    val imageUrlDao: String = "",
    val contractId: String = "0xjklgsf"
)