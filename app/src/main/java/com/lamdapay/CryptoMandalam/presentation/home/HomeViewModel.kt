package com.lamdapay.CryptoMandalam.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lamdapay.CryptoMandalam.common.ListState
import com.lamdapay.CryptoMandalam.common.Resource
import com.lamdapay.CryptoMandalam.domain.model.FundModel
import com.lamdapay.CryptoMandalam.domain.use_case.DonateFund
import com.lamdapay.CryptoMandalam.domain.use_case.GetListOfCrowdFunds
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getListOfCrowdFunds: GetListOfCrowdFunds,
    private val donateFund: DonateFund,
) : ViewModel() {

    private val _state = mutableStateOf(ListState<FundModel>())
    val state: State<ListState<FundModel>> = _state

    init {
        getListOfCrowdFunds(1).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = ListState( stateItemsList = result.data ?: emptyList())
                }
                is Resource.Loading -> {
                    _state.value = ListState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = ListState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun donateFundInCrypto(crowdFundAddress: String, amount: Double){
        viewModelScope.launch {
            donateFund(crowdFundAddress, amount)
        }
    }
}