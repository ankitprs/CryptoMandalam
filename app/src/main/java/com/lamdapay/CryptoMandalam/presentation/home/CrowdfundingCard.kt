package com.lamdapay.CryptoMandalam.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

import coil.compose.rememberImagePainter
import com.lamdapay.CryptoMandalam.R
import com.lamdapay.CryptoMandalam.presentation.ui.theme.ButtonColorOn
import com.lamdapay.CryptoMandalam.presentation.ui.theme.CryptoMandalamTheme


@Composable
fun CrowdfundingCard(
    name: String,
    description: String,
    imageUrl: String,
    pledgedAmount: Double,
    fundingGoal: Double,
    progress: Float,
    daoName: String,
    crowdfundingTiming: String,
    fundCategory: String,
    imageUrlDao: String,
    id: String,
    onDonateClicked: (addressContract: String) -> Unit
) {
    Card(
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp, bottom = 10.dp, start = 10.dp, end = 10.dp),
        elevation = 100.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = rememberImagePainter(
                    data = imageUrl,
                    builder = {
                        placeholder(R.drawable.ic_launcher_background)
                        error(R.drawable.ic_launcher_background)
                    }
                ),
                contentDescription = "background_image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 10.dp)
                ) {
                    Text(
                        text = fundCategory,
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier.weight(1f),
                        fontSize = 15.sp,
                        color = ButtonColorOn
                    )
                    Icon(
                        painter = painterResource(R.drawable.round_access_time_24),
                        contentDescription = "timer_icon"
                    )
                    Text(
                        text = crowdfundingTiming,
                        style = MaterialTheme.typography.caption,
                    )
                }
                Text(
                    text = name,
                    style = MaterialTheme.typography.h6
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 10.dp)
                ) {
                    Image(
                        painter = rememberImagePainter(
                            data = imageUrlDao,
                            builder = {
                                placeholder(R.drawable.round_person_24)
                                error(R.drawable.round_person_24)
                            }
                        ),
                        contentDescription = "background_image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(30.dp)
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(
                        text = daoName,
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .background(Color.Gray)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "$$pledgedAmount",
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "$$fundingGoal",
                        style = MaterialTheme.typography.caption,
                    )
                }
                LinearProgressIndicator(
                    progress = progress,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(15.dp)
                        .padding(vertical = 5.dp),
                )
                Spacer(
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .background(Color.Gray)
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(vertical = 20.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = { onDonateClicked(id) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp),
                    shape = RoundedCornerShape(100.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = ButtonColorOn)
                ) {
                    Text(text = "Donate", modifier = Modifier.padding(5.dp), color = Color.White)
                }
            }
        }
    }
}

@Preview
@Composable
fun Display() {
    CryptoMandalamTheme {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            CrowdfundingCard(
                name = "My Awesome Crowdfunding Project",
                description = "Help me fund my awesome project",
                imageUrl = "https://my-awesome-project.com/image.jpg",
                pledgedAmount = 1000.0,
                fundingGoal = 5000.0,
                progress = 0.2f,
                daoName = "funding_dao",
                crowdfundingTiming = "23th Feb",
                fundCategory = "climte change",
                "",
                "",
                onDonateClicked = { /* Handle donate button clicked */ }
            )
        }
    }
}