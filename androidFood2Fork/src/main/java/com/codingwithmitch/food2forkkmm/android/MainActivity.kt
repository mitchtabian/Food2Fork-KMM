package com.codingwithmitch.food2forkkmm.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import com.codingwithmitch.food2forkkmm.android.presentation.navigation.Navigation
import com.codingwithmitch.food2forkkmm.datasource.network.KtorClientFactory
import com.codingwithmitch.food2forkkmm.datasource.network.RecipeServiceImpl
import com.codingwithmitch.food2forkkmm.datasource.network.model.RecipeDtoMapper
import com.codingwithmitch.food2forkkmm.domain.util.DatetimeUtil
import dagger.hilt.android.AndroidEntryPoint
import io.ktor.client.request.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

const val TOKEN = "Token 9c8b06d329136da358c2d00e76946b0111ce2c48"
const val BASE_URL = "https://food2fork.ca/api/recipe"

@ExperimentalStdlibApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CoroutineScope(IO).launch {
            val recipeService = RecipeServiceImpl(
                recipeDtoMapper = RecipeDtoMapper(),
                httpClient = KtorClientFactory().build(),
                baseUrl = BASE_URL,
            )
            val recipeId = 1551
            val recipe = recipeService.get(recipeId)
            println("KtorTest: ${recipe.title}")
            println("KtorTest: ${recipe.ingredients}")
            println("KtorTest: ${DatetimeUtil().humanizeDatetime(recipe.dateUpdated)}")
        }


        setContent{
            Navigation()
        }
    }
}
