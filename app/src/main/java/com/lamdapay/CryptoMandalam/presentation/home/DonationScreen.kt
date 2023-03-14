package com.lamdapay.CryptoMandalam.presentation.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.lamdapay.CryptoMandalam.presentation.ui.theme.ButtonColorOn
import com.lamdapay.CryptoMandalam.presentation.ui.theme.CryptoMandalamTheme

@Composable
fun DonationScreen(
    navController: NavHostController,
    nameAddress: String,
    onDonateClicked: (donationAmount: Double, onSuccess: () -> Unit) -> Unit
) {
    var donationAmount by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = nameAddress,
                style = MaterialTheme.typography.h6
            )
            OutlinedTextField(
                value = donationAmount,
                onValueChange = { donationAmount = it },
                label = { Text("Donation Amount in SOL") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            Button(
                onClick = {
                    isLoading = true
                    onDonateClicked(donationAmount.toDouble()){
                        Toast.makeText(context, "Payment successful!", Toast.LENGTH_SHORT).show()
                        navController.navigateUp()
                        isLoading = false
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                shape = RoundedCornerShape(100.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = ButtonColorOn)
            ) {
                Text(text = "Donate Now", modifier = Modifier.padding(5.dp), color = Color.White)
            }

        }

        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f)), contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}