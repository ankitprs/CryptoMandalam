package com.lamdapay.CryptoMandalam.domain.model

data class Vote(
    val user: UserModel,
    val choice: Boolean // true for "yes", false for "no"
)