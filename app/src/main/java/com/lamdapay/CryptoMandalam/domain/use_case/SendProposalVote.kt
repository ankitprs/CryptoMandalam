package com.lamdapay.CryptoMandalam.domain.use_case

import android.util.Log
import coil.network.HttpException
import com.lamdapay.CryptoMandalam.domain.model.DaoProposal
import com.lamdapay.CryptoMandalam.domain.model.Proposal
import com.lamdapay.CryptoMandalam.domain.repository.FirestoreRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class SendProposalVote @Inject constructor(
    private val repository: FirestoreRepo
)  {
    operator fun invoke(proposal: DaoProposal) : Flow<Boolean> = flow {
        try {
            if (proposal.solanaDaoAddressId.isEmpty() or proposal.title.isEmpty())
                return@flow
            val sendVote = repository.sendProposalVote(proposal)
            emit(true)
        } catch (e: HttpException) {
            Log.i("SendProposalVote",e.localizedMessage ?: "An unexpected error occurred")
            emit(false)
        } catch (e: IOException) {
            Log.i("SendProposalVote","Couldn't reach server. Check your internet connection.")
            emit(false)
        }
    }
}