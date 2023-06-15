package com.lamdapay.CryptoMandalam.domain.model

data class DaoModel(
    val contractAddress: String,
    val daoName: String,
    val deoDescription: String,
    val daoImagePic:String,
    val creator: UserModel,
    val members: List<UserModel>,
    val proposals: List<Proposal>,
    val tokenSymbol: String,
    val tokenAddress: String,
    val totalFunds: Double,
    )