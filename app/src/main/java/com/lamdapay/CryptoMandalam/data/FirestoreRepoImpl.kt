package com.lamdapay.CryptoMandalam.data


import com.lamdapay.CryptoMandalam.domain.model.*
import com.lamdapay.CryptoMandalam.domain.repository.FirestoreRepo
import com.solana.mobilewalletadapter.clientlib.ActivityResultSender
import com.solana.mobilewalletadapter.clientlib.MobileWalletAdapter
import com.solana.mobilewalletadapter.walletlib.protocol.MobileWalletAdapterServer
import com.solana.mobilewalletadapter.walletlib.scenario.LocalWebSocketServerScenario
import com.solana.mobilewalletadapter.walletlib.scenario.Scenario
import com.solana.mobilewalletadapter.walletlib.scenario.SignAndSendTransactionsRequest
import okhttp3.internal.wait

class FirestoreRepoImpl : FirestoreRepo {

    val testData = TestData()

    override suspend fun getListOfCrowdFunds(page: Int): List<FundModel> {
        return testData.getCrowdFundsList()
    }

    override suspend fun donateFund(crowdFundAddress: String, amount: Double) {
        val wallet = MobileWalletAdapter()
//        val scenario : Scenario = Scenario.Callbacks
//        scenario.start()
//        scenario.associationPublicKey
//        scenario.createMessageReceiver().receiverConnected {
//
//        }
    }

    override suspend fun getListOfProposals(userWalletAddress: String): List<Proposal> {
        return testData.getListOfProposal()
    }

    override suspend fun getProposalDetail(proposalId: String): Proposal {
        return testData.getListOfProposal()[0]
    }

    override suspend fun sendProposalVote(vote: Vote) {
    }

    override suspend fun getDaoList(userWalletAddress: String): List<DaoModel> {
        return testData.getListOfDaoOfUser()
    }
}