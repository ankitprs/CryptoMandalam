package com.lamdapay.CryptoMandalam.domain.model

import com.google.errorprone.annotations.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class FundModel(
    var name: String = "",
    var description: String= "",
    var imageUrl: String= "",
    var pledgedAmount: Double,
    var fundingGoal: Double,
    var progress: Double,
    var daoName: String= "",
    var crowdfundingTiming: String= "",
    var fundCategory: String= "",
    var imageUrlDao: String = "",
    var contractId: String = ""
)