package com.codingwithmitch.food2forkkmm.viewmodel

import android.content.Context
import com.codingwithmitch.food2forkkmm.datasource.Repository

fun DKMPViewModel.Factory.getAndroidInstance(context : Context) : DKMPViewModel {
//    val sqlDriver = AndroidSqliteDriver(LocalDb.Schema, context, "Local.db")
//    val repository = Repository(sqlDriver)
    val repository = Repository()
    return DKMPViewModel(repository)
}