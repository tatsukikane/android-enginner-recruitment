package com.oishikenko.android.recruitment.feature.list

import androidx.compose.foundation.background
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
    var recipeNameNumber = (1..3).random()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(192.dp)
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
                navController.navigate("recipe_detail_screen/$comment/$imageUrl/$recipeType/$recordedAt/$recipeNameNumber")

            },
    ) {
        AsyncImage(
            model = cookingRecord.imageUrl,
            contentDescription = cookingRecord.comment,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(192.dp)
                .clip(RoundedCornerShape(8.dp)),
        )
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(
                    start = 8.dp,
                ),
        ) {
            Text(
                text = when (recipeNameNumber) {
                    1 -> stringResource(id = R.string.recipe_name_1)
                    2 -> stringResource(id = R.string.recipe_name_2)
                    3 -> stringResource(id = R.string.recipe_name_3)
                    else -> ""
                },
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(
                    vertical = 8.dp,
                )
            )
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
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(top = 4.dp)
                    .background(
                        color = Color(0xFFffa500),
                        shape = RoundedCornerShape(20.dp)
                    )
            ) {
                Text(
                    text = when (recipeNameNumber) {
                        1 -> stringResource(id = R.string.recipe_tag_1)
                        2 -> stringResource(id = R.string.recipe_tag_2)
                        3 -> stringResource(id = R.string.recipe_tag_3)
                        else -> ""
                    },
                    fontSize = 12.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(4.dp)
                )
            }
            Spacer(modifier = Modifier.weight(1.0f))
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Spacer(modifier = Modifier.weight(1.0f))
                Text(
                    text = "作成日：${cookingRecord.recordedAt.replace("-", "/").dropLast(9)}",
                    fontSize = 14.sp,
                    color = Color(0xFF676767),
                    modifier = Modifier.padding(end = 16.dp)
                )
            }
        }
    }
}