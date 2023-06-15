package com.lamdapay.CryptoMandalam.domain.use_case

import android.util.Log
import coil.network.HttpException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.lamdapay.CryptoMandalam.common.Resource
import com.lamdapay.CryptoMandalam.domain.model.FundModel
import com.lamdapay.CryptoMandalam.domain.repository.FirestoreRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class GetListOfCrowdFunds @Inject constructor(
    private val repository: FirestoreRepo
) {
    operator fun invoke(page: Int = 1): Flow<Resource<List<FundModel>>> = flow {

        try {
            emit(Resource.Loading())
           val fundModelList = repository.getListOfCrowdFunds(page)
            emit(Resource.Success(fundModelList))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}