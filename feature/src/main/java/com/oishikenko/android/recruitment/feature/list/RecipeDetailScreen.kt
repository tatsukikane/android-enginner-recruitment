package com.oishikenko.android.recruitment.feature.list

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.oishikenko.android.recruitment.data.model.CookingRecord
import com.oishikenko.android.recruitment.feature.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RecipeDetailScreen(
    index: Int,
//    onClick: () -> Unit,
    navController: NavController,
    comment: String,
    //末尾だけ入ってる
    image_url: String,
    recipe_type: String,
    recorded_at: String,
) {
    var imageUrl = "https://cooking-records.ex.oishi-kenko.com/images/$image_url"
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("レシピ",fontWeight = FontWeight.Bold) },
                backgroundColor = Color.White,
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "go back"
                        )
                    }
                },
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
//            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
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
//                        painterResource(R.drawable.main_dish),
                        contentDescription = "food",
                        colorFilter = ColorFilter.tint(Color.White),
                        modifier = Modifier
                            .size(16.dp)

                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text =
                        when (recipe_type) {
                            "main_dish" -> "主菜/主食"
                            "side_dish" -> "副菜"
                            "soup" -> "スープ"
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
            Text(
                text = comment,
                fontSize = 16.sp,
                fontWeight = FontWeight.W500,
                modifier = Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 4.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Spacer(modifier = Modifier.weight(1.0f))
                Text(
                    text = recorded_at.replace("-", "/").dropLast(3),
                    fontSize = 14.sp,
                    color = Color(0xFF676767),
                    modifier = Modifier.padding(end = 16.dp)
                )
            }
        }
    }
}

//@Preview(
//    uiMode = Configuration.UI_MODE_NIGHT_YES,
//    showBackground = true
//)
//@Composable
//fun DetailDefaultPreview() {
//    SampleComposeTheme {
//        Detail(100) {}
//    }
//}