package com.codingwithmitch.food2forkkmm.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import com.codingwithmitch.food2forkkmm.BaseApplication
import com.codingwithmitch.food2forkkmm.android.presentation.navigation.Navigation

@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val model = (application as BaseApplication).model
        setContent{
            Navigation(model)
        }
    }
}
