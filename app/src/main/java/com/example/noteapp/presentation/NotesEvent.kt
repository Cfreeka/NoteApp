package com.example.noteapp.presentation

import com.example.noteapp.Database.Note


sealed interface NotesEvent{

    object SortNotes : NotesEvent

    data class DeleteNote(val note : Note) : NotesEvent
    data class SaveData(
        val title: String,
        val description: String
    ): NotesEvent
    object ShowDialog : NotesEvent
    object HideDialog : NotesEvent

}