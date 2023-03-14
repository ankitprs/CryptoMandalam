package com.lamdapay.CryptoMandalam.presentation.profile

import androidx.lifecycle.ViewModel
import com.lamdapay.CryptoMandalam.domain.use_case.GetDaoList
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getDaoList: GetDaoList
): ViewModel() {

}