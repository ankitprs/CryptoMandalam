package com.lamdapay.CryptoMandalam.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection.Companion.In
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.core.net.toUri
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.lamdapay.CryptoMandalam.presentation.home.DonationScreen
import com.lamdapay.CryptoMandalam.presentation.home.HomeScreen
import com.lamdapay.CryptoMandalam.presentation.profile.ProfileScreen
import com.lamdapay.CryptoMandalam.presentation.proposal.ProposalsScreen
import com.lamdapay.CryptoMandalam.presentation.ui.theme.CryptoMandalamTheme
import com.lamdapay.CryptoMandalam.presentation.ui.theme.bottom_color
import com.solana.pay.SolanaPayTransferRequest
import com.solana.pay.SolanaPayURI
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

//    @Inject
//    lateinit var viewModel: MainViewModel
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val recipientAddress = "3AMtw8m3pZnrGLmGqQcJnHSQSaegtZGBMpon74EVZVQ3"
        val publicKey = recipientAddress.toString()

        setContent {
            CryptoMandalamTheme {
                // A surface container using the 'background' color from the theme
                navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(
                        topBar = {},
                        bottomBar = { BottomNavigationBar(navController) },
                    ) { innerPadding ->
                        NavigationGraph(
                            navController = navController,
                            innerPadding,
                            publicKey = publicKey,
                            this
                        ) { amount, onSuccess ->

//                            val uri = SolanaPayTransferRequest.parse("solana:mvines9iiHiQTysrwkJjGf2gb9Ex9jXJX8ns3qwf2kN?amount=1&label=Michael&message=Thanks%20for%20all%20the%20fish&memo=OrderId12345".toUri()).uri
                            val intent = Intent(Intent.ACTION_VIEW, "solana:mvines9iiHiQTysrwkJjGf2gb9Ex9jXJX8ns3qwf2kN?amount=$amount&label=donation&message=Hope%20my%20contribution%20will%20help".toUri())
                            startActivity(intent)
                        }
                    }
                }
            }
        }
    }

}


@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        Screen.HomeScreen,
        Screen.ProposalsScreen,
        Screen.ProfileScreen,
    )

    BottomNavigation(
        backgroundColor = bottom_color
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        items.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = screen.Icon),
                        contentDescription = null
                    )
                },
                label = { Text(text = screen.text) },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Black.copy(0.5f),
                alwaysShowLabel = true,
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                })
        }
    }
}

@Composable
fun NavigationGraph(
    navController: NavHostController,
    innerPadding: PaddingValues,
    publicKey: String,
    context: Context,
    onDonateClicked: (donationAmount: Int, onSuccess: () -> Unit) -> Unit
) {
    NavHost(
        navController,
        startDestination = Screen.HomeScreen.route,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(Screen.HomeScreen.route) {
            HomeScreen(navController)
        }
        composable(Screen.ProfileScreen.route) {
            ProfileScreen(publicKey, context)
        }
        composable(Screen.ProposalsScreen.route) {
            ProposalsScreen()
        }
        composable(Screen.DonationScreen.route + "/{addressContract}") { backStackEntry ->
            val addressContract = backStackEntry.arguments?.getString("addressContract")
            DonationScreen(navController, addressContract.toString(), onDonateClicked)
        }
    }
}