package com.lamdapay.CryptoMandalam.presentation

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.whenResumed
import com.lamdapay.CryptoMandalam.R
import com.lamdapay.CryptoMandalam.presentation.ui.theme.CryptoMandalamTheme
import com.lamdapay.CryptoMandalam.presentation.ui.theme.PhantomColor
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class AuthenticationActivity : ComponentActivity() {

    @Inject
    lateinit var viewModel: MainViewModel

    private val activityResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            intentSender.onActivityComplete()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoMandalamTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Image(
                            painter = painterResource(id = R.drawable.image_auth_background),
                            contentDescription = "Background Image",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Black.copy(alpha = 0.5f))
                        )

                        PhantomLoginButton{
                            viewModel.authorize(intentSender)
                        }
                    }
                }
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.uiState.collect { uiState ->
                    withContext(Dispatchers.Main) {
                        if (uiState.hasAuthToken) {
                            startActivity(Intent(this@AuthenticationActivity, MainActivity::class.java))
                            finish()
                        }
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.checkIsWalletEndpointAvailable()
    }

    private val intentSender = object : MainViewModel.StartActivityForResultSender {
        private var callback: (() -> Unit)? = null

        override suspend fun startActivityForResult(
            intent: Intent,
            onActivityCompleteCallback: () -> Unit
        ) {
            // A previous Intent may still be pending resolution (via the onActivityComplete method).
            // Wait for the Activity lifecycle to reach the RESUMED state, which guarantees that any
            // previous Activity results will have been received and their callback cleared. Blocking
            // here will lead to either (a) the Activity eventually reaching the RESUMED state, or
            // (b) the Activity terminating, destroying it's lifecycle-linked scope and cancelling this
            // Job.
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
fun PhantomLoginButton(onGoogleLoginClicked: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        Text(
            fontFamily = FontFamily.Cursive,
            text = "Welcome to our crowdfunding app for DAO! Join us in fight against Climate Change, Hunger and many more from around the world.",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold,
            color = Color.LightGray
        )
        Spacer(modifier = Modifier.height(2.dp).background(Color.DarkGray).fillMaxWidth().padding(5.dp))

        Spacer(modifier = Modifier.weight(1F))

        Button(
            onClick = { onGoogleLoginClicked() },
            shape = RoundedCornerShape(100.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = PhantomColor
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 50.dp),

        ) {
            Icon(
                imageVector = ImageVector.run { vectorResource(id = R.drawable.solana_sol_seeklogo_com) },
                contentDescription = "Phantom Login",
                tint = Color.White,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Phantom Connect", color = Color.White)
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    CryptoMandalamTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.image_auth_background),
                contentDescription = "Background Image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.7f))
            )

            PhantomLoginButton{
            }
        }

    }
}