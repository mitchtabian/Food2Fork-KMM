package com.codingwithmitch.food2forkkmm.di

import com.codingwithmitch.food2forkkmm.domain.util.DatetimeUtil

class AppModule {

    val dateUtil: DatetimeUtil by lazy { DatetimeUtil() }
}