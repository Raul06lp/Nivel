package com.example.pmdm.todolist.ui.presentation.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable

@Composable
fun TodoListScreen(){
    Scaffold {innerPadding ->
        LazyColumn(
            contentPadding = innerPadding
        ) {

        }
    }
}