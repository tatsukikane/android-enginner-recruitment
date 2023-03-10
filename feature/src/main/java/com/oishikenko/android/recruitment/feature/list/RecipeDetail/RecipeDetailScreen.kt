package com.oishikenko.android.recruitment.feature.list

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.oishikenko.android.recruitment.feature.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RecipeDetailScreen(
    navController: NavController,
    comment: String,
    image_name: String,
    recipe_type: String,
    recorded_at: String,
    recipe_name_number: Int
) {
    var imageUrl = "https://cooking-records.ex.oishi-kenko.com/images/$image_name"
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.records_detail_title),
                        fontWeight = FontWeight.Bold
                    )
                },
                backgroundColor = Color.White,
                navigationIcon = { HeaderBackButton(navController) },
            )
        }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            ImageAndRightBottomLabelWidget(imageUrl, comment, recipe_type)
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = RecipeNameGenerator(recipe_name_number),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                TagLabelWidget(recipe_name_number)
                Text(
                    text = "[${stringResource(id = R.string.records_detail_title_comment)}]",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
                Text(
                    text = comment,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500,
                )
                Text(
                    text = "[${stringResource(id = R.string.records_detail_title_recipe)}]",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
                Text("?????????????????????????????????????????????\n?????????????????????????????????????????????")
                Row(modifier = Modifier.fillMaxWidth()) {
                    Spacer(modifier = Modifier.weight(1.0f))
                    Text(
                        text = recorded_at.replace("-", "/").dropLast(3),
                        fontSize = 14.sp,
                        color = Color(0xFF676767),
                    )
                }
            }
        }
    }
}

@Composable
fun ImageAndRightBottomLabelWidget(
    imageUrl: String,
    comment: String,
    recipe_type: String,
) {
    Box() {
        AsyncImage(
            model = imageUrl,
            contentDescription = comment,
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(8.dp)
                .height(36.dp)
                .background(
                    color = Color(0xFF063A77),
                    shape = RoundedCornerShape(20.dp)
                )
        ) {
            Spacer(modifier = Modifier.width(16.dp))
            Image(
                painter =
                when (recipe_type) {
                    "main_dish" -> painterResource(R.drawable.main_dish)
                    "side_dish" -> painterResource(R.drawable.side_dish)
                    "soup" -> painterResource(R.drawable.soup)
                    else -> ""
                } as Painter,
                contentDescription = "food",
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier
                    .size(16.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text =
                when (recipe_type) {
                    "main_dish" -> stringResource(id = R.string.recipe_type_main)
                    "side_dish" -> stringResource(id = R.string.recipe_type_side)
                    "soup" -> stringResource(id = R.string.recipe_type_soup)
                    else -> ""
                },
                fontSize = 14.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
            )
            Spacer(modifier = Modifier.width(16.dp))
        }
    }
}