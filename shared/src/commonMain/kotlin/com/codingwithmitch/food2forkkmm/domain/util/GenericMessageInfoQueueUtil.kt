package com.codingwithmitch.food2forkkmm.domain.util

import com.codingwithmitch.food2forkkmm.domain.model.GenericMessageInfo

/**
 * Normally I would just make an extension function but KMP cannot use extension functions yet
 */
class GenericMessageInfoQueueUtil() {
    /**
     * Does this particular GenericMessageInfo already exist in the queue? We don't want duplicates
     */
    fun doesMessageAlreadyExistInQueue(queue: Queue<GenericMessageInfo>, messageInfo: GenericMessageInfo): Boolean{
        for(item in queue.items){
            if (item.id == messageInfo.id){
                return true
            }
        }
        return false
    }
}