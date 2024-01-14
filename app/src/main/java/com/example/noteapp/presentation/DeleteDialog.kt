package com.example.noteapp.presentation

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import com.example.noteapp.Database.Note

@Composable
fun DeleteDialog(
    state: NoteState,
    index: Int,
    note: Note,
    onEvent: (NotesEvent)-> Unit
) {
    AlertDialog(
        title = {
                Text(
                    text = "Delete note",

                )
        },
        text = {
               Text(
                   text = "Are you sure you want to delete the note?",

               )

        },
        onDismissRequest = {onEvent(NotesEvent.HideDialog) },
        confirmButton = {
            Button(onClick = {
                onEvent(NotesEvent.DeleteNote(note))

            }) {
                Text(text = "Yes")
            }
        },
        dismissButton = {
            Button(onClick = {
                onEvent(NotesEvent.HideDialog)
            }) {
                Text(text = "No")
            }
        }
    )

}