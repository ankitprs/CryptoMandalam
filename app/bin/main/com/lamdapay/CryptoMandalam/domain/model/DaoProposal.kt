package com.lamdapay.CryptoMandalam.domain.model

data class DaoProposal(
    val title: String,
    val daoName: String,
    val amountToRaise: String,
    val fundDescription: String,
    val solanaDaoAddressId: String
)
