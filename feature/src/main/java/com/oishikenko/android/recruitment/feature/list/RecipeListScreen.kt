package com.oishikenko.android.recruitment.feature.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.oishikenko.android.recruitment.feature.R

@OptIn(ExperimentalLayoutApi::class, ExperimentalLifecycleComposeApi::class)
@Composable
fun RecipeListScreen(
    viewModel: RecipeListViewModel = hiltViewModel()
) {
    var cookingRecords = viewModel.cookingRecordsPager.collectAsLazyPagingItems()
    val navController = rememberNavController()

    Scaffold { innerPadding ->
        NavHost(navController = navController, startDestination = "main") {
            composable("main") {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = R.string.cooking_records_title),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Image(
                            painter = painterResource(R.drawable.top_bar_icon),
                            contentDescription = "food",
                            modifier = Modifier
                                .size(64.dp)
                        )
                    }
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(innerPadding)
                            .consumedWindowInsets(innerPadding)
                    ) {
                        items(cookingRecords) { item ->
                            item?.let {
                                RecipeListItem(
                                    cookingRecord = it,
                                    navController = navController
                                )
                            }
                        }
                        when (cookingRecords.loadState.append) {
                            is LoadState.Error -> Unit
                            LoadState.Loading -> {
                                item {
                                    LoadingItem()
                                }
                            }
                            is LoadState.NotLoading -> {
                                item {
                                    LoadingItem()
                                }
                            }
                        }
                    }
                }
            }
            composable(
                "recipe_detail/{comment}/{image_name}/{recipe_type}/{recorded_at}", arguments = listOf(
                    navArgument("comment") { type = NavType.StringType },
                    navArgument("image_name") { type = NavType.StringType },
                    navArgument("recipe_type") { type = NavType.StringType },
                    navArgument("recorded_at") { type = NavType.StringType },

                    )
            ) { backStackEntry ->
                val comment = backStackEntry.arguments?.getString("comment") ?: ""
                val image_name = backStackEntry.arguments?.getString("image_name") ?: ""
                val recipe_type = backStackEntry.arguments?.getString("recipe_type") ?: ""
                val recorded_at = backStackEntry.arguments?.getString("recorded_at") ?: ""

                RecipeDetailScreen(
                    index = 1,
                    navController = navController,
                    comment = comment,
                    image_name = image_name,
                    recipe_type = recipe_type,
                    recorded_at = recorded_at
                )
            }
        }
    }
}

@Composable
fun LoadingItem() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .width(42.dp)
                .height(42.dp)
                .padding(8.dp),
            strokeWidth = 5.dp
        )
    }
}

@Composable
fun ErrorItem(message: String) {
    Card(
        elevation = 2.dp,
        modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Red)
                .padding(8.dp)
        ) {
            Text(
                color = Color.White,
                text = message,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(start = 12.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}


@Preview
@Composable
fun PreviewRecipeListScreen() {
    MaterialTheme {
        RecipeListScreen()
    }
}
