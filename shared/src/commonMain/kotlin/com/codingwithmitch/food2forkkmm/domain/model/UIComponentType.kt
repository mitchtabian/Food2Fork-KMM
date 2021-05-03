package com.codingwithmitch.shared.domain.util

sealed class UIComponentType{

    object Dialog: UIComponentType()

    object None: UIComponentType()
}