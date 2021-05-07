package com.codingwithmitch.food2forkkmm.android.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.codingwithmitch.food2forkkmm.domain.model.GenericMessageInfo
import com.codingwithmitch.food2forkkmm.domain.util.Queue
import com.codingwithmitch.food2forkkmm.util.Logger

@Composable
fun ProcessDialogQueue(
    dialogQueue: Queue<GenericMessageInfo>?,
) {
    val logger = remember{ Logger("ProcessDialogQueue") }
    dialogQueue?.peek()?.let { dialogInfo ->
        GenericDialog(
            onDismiss = dialogInfo.onDismiss,
            title = dialogInfo.title,
            description = dialogInfo.description,
            positiveAction = dialogInfo.positiveAction,
            negativeAction = dialogInfo.negativeAction,
            onRemoveHeadFromQueue = {
                try {
                    dialogQueue.remove()
                }catch (e: Exception){
                    logger.log("Nothing to remove from DialogQueue")
                }
            }
        )
    }
}