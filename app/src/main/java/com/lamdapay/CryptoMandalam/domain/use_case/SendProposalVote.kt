package com.lamdapay.CryptoMandalam.domain.use_case

import android.util.Log
import coil.network.HttpException
import com.lamdapay.CryptoMandalam.domain.model.Vote
import com.lamdapay.CryptoMandalam.domain.repository.FirestoreRepo
import java.io.IOException
import javax.inject.Inject

class SendProposalVote @Inject constructor(
    private val repository: FirestoreRepo
)  {
    suspend operator fun invoke(vote: Vote)  {
        try {
            val sendVote = repository.sendProposalVote(vote)
        } catch (e: HttpException) {
            Log.i("SendProposalVote",e.localizedMessage ?: "An unexpected error occurred")
        } catch (e: IOException) {
            Log.i("SendProposalVote","Couldn't reach server. Check your internet connection.")
        }
    }
}