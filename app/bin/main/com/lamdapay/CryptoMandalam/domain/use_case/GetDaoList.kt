package com.lamdapay.CryptoMandalam.domain.use_case

import coil.network.HttpException
import com.lamdapay.CryptoMandalam.common.Resource
import com.lamdapay.CryptoMandalam.domain.model.DaoModel
import com.lamdapay.CryptoMandalam.domain.repository.FirestoreRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetDaoList @Inject constructor(
    private val repository: FirestoreRepo
)  {
    operator fun invoke(userWalletAddress: String): Flow<Resource<List<DaoModel>>> = flow {
        try {
            emit(Resource.Loading())
            val proposalList = repository.getDaoList(userWalletAddress)
            emit(Resource.Success(proposalList))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}