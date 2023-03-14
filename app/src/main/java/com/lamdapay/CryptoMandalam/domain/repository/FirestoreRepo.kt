package com.lamdapay.CryptoMandalam.domain.repository

import com.lamdapay.CryptoMandalam.domain.model.*

interface FirestoreRepo {

    suspend fun getListOfCrowdFunds(page: Int): List<FundModel>

    suspend fun donateFund(crowdFundAddress: String, amount: Double)

    suspend fun getListOfProposals(userWalletAddress: String): List<Proposal>

    suspend fun getProposalDetail(proposalId: String): Proposal

    suspend fun sendProposalVote(vote: Vote)

    suspend fun getDaoList(userWalletAddress: String): List<DaoModel>

}