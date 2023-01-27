package com.oishikenko.android.recruitment.feature.list

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.oishikenko.android.recruitment.feature.R

@Composable
fun RecipeTagGenerator(
    recipe_name_number: Int,
): String {
    return when (recipe_name_number) {
        1 -> stringResource(id = R.string.recipe_tag_1)
        2 -> stringResource(id = R.string.recipe_tag_2)
        3 -> stringResource(id = R.string.recipe_tag_3)
        else -> ""
    }
}