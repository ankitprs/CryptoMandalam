package com.lamdapay.CryptoMandalam.presentation

import com.lamdapay.CryptoMandalam.R

sealed class Screen(val route: String, val Icon: Int, val text: String){
    object HomeScreen : Screen("home_screen", R.drawable.round_home_24, "Home")
    object ProfileScreen: Screen("profile_screen", R.drawable.round_person_24, "Profile")
    object ProposalsScreen: Screen("proposals_screen",R.drawable.baseline_lightbulb_24, "Proposals")
    object DonationScreen: Screen("donation_screen", R.drawable.round_home_24, "Donation")
}
