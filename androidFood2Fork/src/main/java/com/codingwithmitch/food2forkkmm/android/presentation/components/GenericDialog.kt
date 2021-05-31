package com.codingwithmitch.food2forkkmm.android.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GenericDialog(
    title: String,
    description: String? = null,
) {
    AlertDialog(
        onDismissRequest = {
            // TODO("remove message from queue")
        },
        text = {
            if (description != null) {
                Text(
                    text = description,
                    style = MaterialTheme.typography.body1
                )
            }
        },
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.h3
            )
        },
        buttons = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.End,
            ){
                Button(
                    modifier = Modifier.padding(end = 8.dp),
                    onClick = {
                        // TODO("remove message from queue")
                    }
                ) {
                    Text(text = "Cancel")
                }
                Button(
                    modifier = Modifier.padding(end = 8.dp),
                    onClick = {
                        // TODO("remove message from queue")
                    }
                ) {
                    Text(text = "OK")
                }
            }
        }
    )
}