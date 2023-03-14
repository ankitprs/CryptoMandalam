package com.lamdapay.CryptoMandalam.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.lamdapay.CryptoMandalam.domain.model.DaoModel


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProfileScreen(
    userPublicKey: String,
    viewModel: ProfileViewModel = hiltViewModel()
) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "My Profile",
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp)
        )

        Text(
            text = userPublicKey,
            fontSize = 24.sp,
            fontWeight = FontWeight.Black,
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp)
        )
        // Clickable list of options
        ListItem(
            text = { Text("Create new DAO") },
            modifier = Modifier.clickable { /* Handle DAO click */ }
        )

        ListItem(
            text = { Text("Create new fund proposal") },
            modifier = Modifier.clickable { /* Handle proposal click */ }
        )

        ListItem(
            text = { Text("Contact me") },
            modifier = Modifier.clickable { /* Handle contact click */ }
        )
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
