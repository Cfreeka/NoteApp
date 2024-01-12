package com.example.noteapp.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.noteapp.Database.Note

data class NoteState(
    val title: MutableState<String> = mutableStateOf(""),
    val description: MutableState<String> = mutableStateOf(""),
    val notes: List<Note> = emptyList()
)