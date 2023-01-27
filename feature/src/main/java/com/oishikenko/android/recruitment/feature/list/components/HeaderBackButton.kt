package com.oishikenko.android.recruitment.feature.list

import android.annotation.SuppressLint
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HeaderBackButton(
    navController: NavController,
) {
    IconButton(onClick = { navController.navigateUp() }) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "go back"
        )
    }
}