package com.example.noteapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Sort
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.noteapp.Database.Note
import com.example.noteapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen(
    state: NoteState,
    navController: NavController,
    onEvent: (NotesEvent) -> Unit,
) {
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    modifier = Modifier.weight(1f),
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold
                )
                IconButton(onClick = {
                    onEvent(NotesEvent.SortNotes)
                }) {
                    Icon(
                        imageVector = Icons.Rounded.Sort,
                        contentDescription = "Sort",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )

                }

            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                state.title.value = ""
                state.description.value = ""
                navController.navigate("AddNotesScreen")
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add notes"
                )

            }
        }


    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)

        ) {
            items(state.notes.size) { index ->
                NoteItem(
                    state = state,
                    index = index,
                    onEvent = onEvent,
                    navController = navController
                )
            }


        }

    }

}

@Composable
fun NoteItem(
    state: NoteState,
    index: Int,
    navController: NavController,
    onEvent: (NotesEvent) -> Unit
) {
    if (state.isDeletingNote) {
        DeleteDialog(state = state, index = index, onEvent = onEvent)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(12.dp)
            .clickable {
                state.title.value =state.notes[index].title
                state.description.value = state.notes[index].description
                navController.navigate("AddNotesScreen")
            }

    ) {

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = state.notes[index].title,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                maxLines = 1,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = state.notes[index].description,
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                maxLines = 1

            )

        }


        IconButton(onClick = {
           onEvent(NotesEvent.ShowDialog)

        }) {
            Icon(
                imageVector = Icons.Rounded.Delete,
                contentDescription = "Delete note",
                modifier = Modifier.size(35.dp),
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )

        }

    }

}
