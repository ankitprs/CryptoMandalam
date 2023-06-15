package com.lamdapay.CryptoMandalam.data


import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
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

    override suspend fun sendProposalVote(proposal: DaoProposal) {
        val document = FirebaseFirestore.getInstance().collection("daoProposals")
            .document(proposal.solanaDaoAddressId)

        document.set(this)
            .addOnSuccessListener {
                // Successfully saved the dao proposal
            }
            .addOnFailureListener {
                // Failed to save the dao proposal
            }
    }

    override suspend fun getDaoList(userWalletAddress: String): List<DaoModel> {
        return testData.getListOfDaoOfUser()
    }
}