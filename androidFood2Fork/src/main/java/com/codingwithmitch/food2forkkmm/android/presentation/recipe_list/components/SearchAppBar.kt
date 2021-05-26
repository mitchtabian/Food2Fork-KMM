package com.codingwithmitch.food2forkkmm.android.presentation.recipe_list.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.codingwithmitch.food2forkkmm.presentation.recipe_list.FoodCategory
import com.codingwithmitch.food2forkkmm.presentation.recipe_list.FoodCategoryUtil

@ExperimentalComposeUiApi
@Composable
fun SearchAppBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    onExecuteSearch: () -> Unit,
    categories: List<FoodCategory>,
    selectedCategory: FoodCategory?,
    onSelectedCategoryChanged: (FoodCategory) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = MaterialTheme.colors.secondary,
        elevation = 8.dp,
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth(.9f)
                        .padding(8.dp)
                    ,
                    value = query,
                    onValueChange = { onQueryChanged(it) },
                    label = { Text(text = "Search") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done,
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            onExecuteSearch()
                            keyboardController?.hide()
                        },
                    ),
                    leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search Icon") },
                    textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = MaterialTheme.colors.surface),
                )
            }
            LazyRow(
                modifier = Modifier
                    .padding(start = 8.dp, bottom = 8.dp),
            ) {
                items(categories) {
                    FoodCategoryChip(
                        category = it.value,
                        isSelected = selectedCategory == it,
                        onSelectedCategoryChanged = {
                            FoodCategoryUtil().getFoodCategory(it)?.let{ newCategory ->
                                onSelectedCategoryChanged(newCategory)
                            }
                        },
                    )
                }
            }
        }
    }
}







