package com.lamdapay.CryptoMandalam.presentation.profile

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.lamdapay.CryptoMandalam.domain.model.DaoModel
import com.lamdapay.CryptoMandalam.presentation.ui.theme.bottom_color


@Composable
fun ProfileScreen(
    userPublicKey: String,
    context: Context,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "My Profile...",
            fontSize = 32.sp,
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        )
        Text(
            text = "  Wallet Address -> $userPublicKey ",
            fontSize = 16.sp,
            fontWeight = FontWeight.Black,
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp)
        )
        // Clickable list of options
        BeautifulListItem(text = "Create new DAO") {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://forms.gle/yjw9u6MDTCFPWnZu6")))
        }
        BeautifulListItem(text = "Create new Fund proposal") {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://forms.gle/UMbW9mxFhiuK3JpH7")))
        }
        BeautifulListItem(text = "Contact me") {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://forms.gle/xTfALXjxT5QhXroj7")))
        }
    }

}

@Composable
fun DaoCardView(dao: DaoModel, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // DAO image
            Image(
                painter = rememberAsyncImagePainter(dao.daoImagePic),
                contentDescription = dao.daoName,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // DAO name
            Text(
                text = dao.daoName,
                style = MaterialTheme.typography.h5
            )

            Spacer(modifier = Modifier.height(8.dp))

            // DAO description
            Text(
                text = dao.deoDescription,
                style = MaterialTheme.typography.body1
            )

            Spacer(modifier = Modifier.height(16.dp))

            // DAO creator
            Text(
                text = "Created by ${dao.creator.name}",
                style = MaterialTheme.typography.caption
            )

            Spacer(modifier = Modifier.height(8.dp))

            // DAO members
            Text(
                text = "Members: ${dao.members.size}",
                style = MaterialTheme.typography.caption
            )
        }
    }
}

@Composable
fun BeautifulListItem(text: String, onClick: () -> Unit) {
    Button(
        modifier = Modifier.padding(10.dp).fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(backgroundColor = bottom_color),
        shape = RoundedCornerShape(30.dp),
        onClick = onClick
    ) {
        Text(
            modifier = Modifier.padding(15.dp),
            text = text,
            color = Color.DarkGray,
            fontSize = 14.sp
        )
    }
}