package com.lamdapay.CryptoMandalam.domain.use_case

import com.lamdapay.CryptoMandalam.domain.repository.FirestoreRepo
import javax.inject.Inject

class DonateFund @Inject constructor(
    private val repository: FirestoreRepo
)  {
    suspend operator fun invoke(crowdFundAddress: String, amount: Double) {
        try {
            repository.donateFund(crowdFundAddress, amount)
        }catch (e: Exception){

        }
    }
}