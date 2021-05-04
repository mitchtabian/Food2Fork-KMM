package com.codingwithmitch.food2forkkmm.android.presentation.util

import com.codingwithmitch.food2forkkmm.domain.model.GenericMessageInfo
import com.codingwithmitch.food2forkkmm.domain.util.Queue

/**
 * Does this particular GenericMessageInfo already exist in the queue? We don't want duplicates
 */
fun Queue<GenericMessageInfo>.doesMessageAlreadyExistInQueue(messageInfo: GenericMessageInfo): Boolean{
    for(item in items){
        if (item.id == messageInfo.id){
            return true
        }
    }
    return false
}