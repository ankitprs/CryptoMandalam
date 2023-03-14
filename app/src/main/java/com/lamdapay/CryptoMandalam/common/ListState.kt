package com.lamdapay.CryptoMandalam.common

data class ListState<T>(
    val isLoading: Boolean = false,
    val stateItemsList: List<T> = emptyList(),
    val error: String = ""
)