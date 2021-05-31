package com.codingwithmitch.food2forkkmm.domain.model

sealed class UIComponentType{

    object Dialog: UIComponentType()

    object None: UIComponentType()
}