package com.oishikenko.android.recruitment.feature.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.oishikenko.android.recruitment.feature.R

@OptIn(ExperimentalLayoutApi::class, ExperimentalLifecycleComposeApi::class)
@Composable
fun RecipeListScreen(
    viewModel: RecipeListViewModel = hiltViewModel(),
    navController: NavController
) {
    var cookingRecords = viewModel.cookingRecordsPager.collectAsLazyPagingItems()
    Scaffold { innerPadding ->
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
            Box() {
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
                        is LoadState.NotLoading -> Unit
                        LoadState.Loading -> {
                            item {
                                LoadingItem()
                            }
                        }
                        is LoadState.Error -> {
                            item {
                                ErrorItem(message = "エラーが発生しました")
                            }
                        }
                    }
                    //アプリ起動後の初回ローディング
                    when (cookingRecords.loadState.refresh) {
                        is LoadState.NotLoading -> Unit
                        LoadState.Loading -> {
                            item {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentHeight(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator()
                                }
                            }
                        }
                        is LoadState.Error -> {
                            item {
                                ErrorItem(message = "エラーが発生しました")
                            }
                        }
                    }
                }
                FloatingActionButton(
                    onClick = { navController.navigate("post_recipe_screen") },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(12.dp),
                    backgroundColor = Color(0xFF87cefa)
                ) {
                    Icon(Icons.Filled.Add, tint = Color.White, contentDescription = "追加")
                }
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
