package com.lamdapay.CryptoMandalam.presentation

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.core.net.toUri
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.whenResumed
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.lamdapay.CryptoMandalam.domain.model.FundModel
import com.lamdapay.CryptoMandalam.presentation.home.DonationScreen
import com.lamdapay.CryptoMandalam.presentation.home.HomeScreen
import com.lamdapay.CryptoMandalam.presentation.profile.ProfileScreen
import com.lamdapay.CryptoMandalam.presentation.proposal.ProposalsScreen
import com.lamdapay.CryptoMandalam.presentation.ui.theme.CryptoMandalamTheme
import com.lamdapay.CryptoMandalam.presentation.ui.theme.bottom_color
import com.solana.pay.SolanaPayTransferRequest
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.bouncycastle.asn1.x509.ObjectDigestInfo.publicKey
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModel: MainViewModel
    private lateinit var navController: NavHostController

    private val activityResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            intentSender.onActivityComplete()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val publicKey = viewModel.uiState.value.publicKey.toString()
        val recipientAddress = "3AMtw8m3pZnrGLmGqQcJnHSQSaegtZGBMpon74EVZVQ3"

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

                            SolanaPayTransferRequest("solana:mvines9iiHiQTysrwkJjGf2gb9Ex9jXJX8ns3qwf2kN?amount=1&label=Michael&message=Thanks%20for%20all%20the%20fish&memo=OrderId12345".toUri())
//                            viewModel.signAndSendTransactions(intentSender, 1000000)
                        }
                    }
                }
            }
        }
    }

    private val intentSender = object : MainViewModel.StartActivityForResultSender {
        private var callback: (() -> Unit)? = null

        override suspend fun startActivityForResult(
            intent: Intent,
            onActivityCompleteCallback: () -> Unit
        ) {
            lifecycle.whenResumed { // NOTE: runs in Dispatchers.MAIN context
                check(callback == null) { "Received an activity start request while another is pending" }
                callback = onActivityCompleteCallback

                try {
                    activityResultLauncher.launch(intent)
                } catch (e: ActivityNotFoundException) {
                    callback = null
                    throw e
                }
            }
        }

        fun onActivityComplete() {
            callback?.let { it() }
            callback = null
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