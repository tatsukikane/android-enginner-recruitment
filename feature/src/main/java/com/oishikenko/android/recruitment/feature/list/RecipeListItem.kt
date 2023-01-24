package com.oishikenko.android.recruitment.feature.list

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.oishikenko.android.recruitment.data.model.CookingRecord

@Composable
fun RecipeListItem(
    cookingRecord: CookingRecord
) {
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
            ),
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
                //TODO: ロジック側で処理したい
                when (cookingRecord.recipeType) {
                    "main_dish" -> "主菜/主食"
                    "side_dish" -> "主催"
                    "soup" -> "スープ"
                    else -> ""
                },
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
//                text = cookingRecord.recordedAt,
                text = cookingRecord.recordedAt.replace("-", "/").dropLast(3),
                fontSize = 14.sp,
            )
        }
    }
}

@Preview
@Composable
fun PreviewRecipeListItem() {
    RecipeListItem(
        cookingRecord = CookingRecord(
            imageUrl = "",
            comment = "豚肉のコクとごぼうの香り、野菜の甘みで奥行きのある味わい。",
            recipeType = "soup",
            recordedAt = "2018-05-01 17:57:31"
        )
    )
}