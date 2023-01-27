package com.oishikenko.android.recruitment.feature.list

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "recipe_list_screen") {
        composable("recipe_list_screen") { RecipeListScreen(hiltViewModel(), navController) }
        composable(
            "recipe_detail_screen/{comment}/{image_name}/{recipe_type}/{recorded_at}/{recipe_name_number}",
            arguments = listOf(
                navArgument("comment") { type = NavType.StringType },
                navArgument("image_name") { type = NavType.StringType },
                navArgument("recipe_type") { type = NavType.StringType },
                navArgument("recorded_at") { type = NavType.StringType },
                navArgument("recipe_name_number") { type = NavType.IntType },

                )
        ) { backStackEntry ->
            val comment = backStackEntry.arguments?.getString("comment") ?: ""
            val image_name = backStackEntry.arguments?.getString("image_name") ?: ""
            val recipe_type = backStackEntry.arguments?.getString("recipe_type") ?: ""
            val recorded_at = backStackEntry.arguments?.getString("recorded_at") ?: ""
            val recipe_name_number = backStackEntry.arguments?.getInt("recipe_name_number") ?: 1

            RecipeDetailScreen(
                navController = navController,
                comment = comment,
                image_name = image_name,
                recipe_type = recipe_type,
                recorded_at = recorded_at,
                recipe_name_number = recipe_name_number
            )
        }
        composable("post_recipe_screen") { PostRecipeScreen(navController) }
    }
}