package com.lamdapay.CryptoMandalam.presentation.proposal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lamdapay.CryptoMandalam.domain.model.DaoProposal
import com.lamdapay.CryptoMandalam.domain.use_case.SendProposalVote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class ProposalViewModel @Inject constructor(
    private val sendProposalDao: SendProposalVote
): ViewModel() {

    fun sendProposal(proposal: DaoProposal) {
        sendProposalDao(proposal).onEach{

        }.launchIn(viewModelScope)
    }
}