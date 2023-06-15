package com.lamdapay.CryptoMandalam.presentation.proposal

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lamdapay.CryptoMandalam.domain.model.DaoProposal

@Composable
fun ProposalsScreen(
    viewModel: ProposalViewModel = hiltViewModel()
) {
    val daoProposals = remember { mutableStateListOf<DaoProposal>() }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "DAO Proposal Form",
            fontSize = 32.sp,
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
        DaoProposalForm(onFormSubmit = { proposal ->
            viewModel.sendProposal(proposal)
            Toast.makeText(context, "Proposal is Send for review", Toast.LENGTH_LONG).show()
        })
    }
}


@Composable
fun DaoProposalForm(
    onFormSubmit: (DaoProposal) -> Unit
) {
    val title = remember { mutableStateOf("") }
    val daoName = remember { mutableStateOf("") }
    val amountToRaise = remember { mutableStateOf("") }
    val fundDescription = remember { mutableStateOf("") }
    val solanaDaoAddressId = remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.align(Alignment.Center)) {

            TextField(
                value = title.value,
                onValueChange = { title.value = it },
                label = { Text("Title") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Blue,
                    unfocusedBorderColor = Color.Gray,
                )
            )

            TextField(
                value = daoName.value,
                onValueChange = { daoName.value = it },
                label = { Text("DAO Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Blue,
                    unfocusedBorderColor = Color.Gray,
                )
            )

            TextField(
                value = amountToRaise.value,
                onValueChange = { amountToRaise.value = it },
                label = { Text("Amount to Raise") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Blue,
                    unfocusedBorderColor = Color.Gray,
                )
            )

            TextField(
                value = fundDescription.value,
                onValueChange = { fundDescription.value = it },
                label = { Text("Fund Description") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Blue,
                    unfocusedBorderColor = Color.Gray,
                )
            )

            TextField(
                value = solanaDaoAddressId.value,
                onValueChange = { solanaDaoAddressId.value = it },
                label = { Text("Solana DAO Address ID") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Blue,
                    unfocusedBorderColor = Color.Gray,
                )
            )

            Button(
                onClick = {
                    val daoProposal = DaoProposal(
                        title = title.value,
                        daoName = daoName.value,
                        amountToRaise = amountToRaise.value,
                        fundDescription = fundDescription.value,
                        solanaDaoAddressId = solanaDaoAddressId.value
                    )
                    onFormSubmit(daoProposal)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp)
                    .height(60.dp),
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue)
            ) {
                Text(
                    text = "Submit Proposal",
                    modifier = Modifier.padding(5.dp),
                    color = Color.White
                )
            }
        }
    }
}
