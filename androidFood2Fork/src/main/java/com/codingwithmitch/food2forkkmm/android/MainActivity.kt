package com.codingwithmitch.food2forkkmm.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import com.codingwithmitch.food2forkkmm.android.presentation.navigation.Navigation
import com.codingwithmitch.food2forkkmm.datasource.network.KtorClientFactory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val ktorClient = KtorClientFactory().build()
        val recipe = ktorClient.get{
            url("$baseUrl/get?id=$id")
            header("Authorization", TOKEN)
        }

        setContent{
            Navigation()
        }
    }
}
