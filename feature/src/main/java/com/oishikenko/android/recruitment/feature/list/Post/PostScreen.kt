package com.oishikenko.android.recruitment.feature.list

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.oishikenko.android.recruitment.feature.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PostRecipeScreen(
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.cooking_records_title),
                        fontWeight = FontWeight.Bold
                    )
                },
                backgroundColor = Color.White,
                navigationIcon = { HeaderBackButton(navController) },
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Box(modifier = Modifier.height(288.dp)) {
                AsyncImage(
                    model = "https://cooking-records.ex.oishi-kenko.com/images/3.jpg",
                    contentDescription = "food",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxWidth()
                )
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    IconButton(onClick = { }) {
                        Icon(
                            painter = rememberVectorPainter(image = Icons.Default.AddCircle),
                            contentDescription = "go back",
                            modifier = Modifier.size(72.dp),
                            tint = Color.White
                        )
                    }
                }
            }
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                TitleAndTextField(stringResource(id = R.string.records_detail_title_recipe_name))
                TitleAndTextField(stringResource(id = R.string.records_detail_title_comment))
                TitleAndTextField(stringResource(id = R.string.records_detail_title_recipe))
                RegisterButton()
            }
        }
    }
}

@Composable
fun TitleAndTextField(
    title: String
) {
    var text by remember { mutableStateOf("") }
    Text(
        text = "[${title}]",
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(
            vertical = 16.dp,
        )
    )
    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(title) }
    )
}

@Composable
fun RegisterButton(
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 8.dp)
    ) {
        Spacer(modifier = Modifier.weight(1.0f))
        Button(
            onClick = { /* Do something */ },
            shape = RoundedCornerShape(100),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF87cefa))
        ) {
            Text(
                text = stringResource(id = R.string.register),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White,
            )
        }
        Spacer(modifier = Modifier.weight(1.0f))
    }
}