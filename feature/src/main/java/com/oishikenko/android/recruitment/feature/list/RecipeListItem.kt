package com.oishikenko.android.recruitment.feature.list

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.oishikenko.android.recruitment.data.model.CookingRecord
import com.oishikenko.android.recruitment.feature.R

@Composable
fun RecipeListItem(
    cookingRecord: CookingRecord,
    navController: NavController
) {
    var comment = cookingRecord.comment
    var imageUrl = cookingRecord.imageUrl.split("/")[4]
    var recipeType = cookingRecord.recipeType
    var recordedAt = cookingRecord.recordedAt
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(96.dp)
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp,
            )
            .border(
                width = 1.dp,
                color = Color(0xFFDCE0E0),
                shape = RoundedCornerShape(8.dp)
            )
            .clickable {
                navController.navigate("recipe_detail_screen/$comment/$imageUrl/$recipeType/$recordedAt")

            },
    ) {
        AsyncImage(
            model = cookingRecord.imageUrl,
            contentDescription = cookingRecord.comment,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                //TODO: 高さ指定、定数を入れずにやるやり方がありそう
                .size(96.dp)
                .clip(RoundedCornerShape(8.dp)),
        )
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(
                    start = 8.dp,
                ),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text =
                when (cookingRecord.recipeType) {
                    "main_dish" -> stringResource(id = R.string.recipe_type_main)
                    "side_dish" -> stringResource(id = R.string.recipe_type_side)
                    "soup" -> stringResource(id = R.string.recipe_type_soup)
                    else -> ""
                },
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = cookingRecord.recordedAt.replace("-", "/").dropLast(3),
                fontSize = 14.sp,
            )
        }
    }
}