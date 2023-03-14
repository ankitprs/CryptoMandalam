package com.lamdapay.CryptoMandalam.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.lamdapay.CryptoMandalam.R
import com.lamdapay.CryptoMandalam.common.ListState
import com.lamdapay.CryptoMandalam.domain.model.FundModel
import com.lamdapay.CryptoMandalam.presentation.Screen


@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Welcome...",
            fontSize = 32.sp,
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
        CrowdFundingListView(
            state = ListState(
                stateItemsList = viewModel.state.value.stateItemsList
            )
        ) { addressContract ->
            navController.navigate(Screen.DonationScreen.route + "/" + addressContract)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CrowdFundingListView(
    state: ListState<FundModel>,
    onDonateClicked: (addressContract: String) -> Unit
) {
    val pageCount = state.stateItemsList.size
    val pagerState = rememberPagerState()

    LaunchedEffect(Unit) {
//        while (true) {
//            yield()
//            pagerState.animateScrollToPage(
//                page = (pagerState.currentPage),
//                //% (state.consultantList.size),
//                animationSpec = tween(200)
//            )
//        }
    }
    VerticalPager(
        state = pagerState,
        modifier = Modifier,
        pageCount = pageCount
    ) { page ->
        val fund = state.stateItemsList[page]
        fund.apply {
            CrowdfundingCard(
                name,
                description,
                imageUrl,
                pledgedAmount,
                fundingGoal,
                progress.toFloat(),
                daoName,
                crowdfundingTiming,
                fundCategory,
                imageUrlDao,
                contractId,
                onDonateClicked
            )
        }
    }
}