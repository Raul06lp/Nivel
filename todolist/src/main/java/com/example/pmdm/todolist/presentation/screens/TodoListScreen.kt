package com.example.pmdm.todolist.presentation.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.example.pmdm.todolist.data.local.entities.Todo
import com.example.pmdm.todolist.presentation.components.TodoListItem
import com.example.pmdm.todolist.ui.theme.NivelTheme

@Composable
fun TodoListScreen(
    todos: List<Todo> = emptyList(),
    onTodoCheck: (Todo, Boolean) -> Unit = {_,_ ->},
    onTodoDelete: (Todo) -> Unit = {}
){
    Scaffold {innerPadding ->
        LazyColumn(
            contentPadding = innerPadding
        ) {
            items(
                todos,
                key = { todo -> todo.id }
            ) {
                TodoListItem(
                    it,
                    onTodoCheck,
                    onTodoDelete
                )
            }
        }
    }
}

@Preview(
    showSystemUi = true
)
@Composable
fun TodoListScreenPreview() {

    val todos = remember {
        mutableStateListOf(
            listOf(
                Todo(1, "Lavar el coche"),
                Todo(1, "Estudiar kotlin", isDone = true),
                Todo(1, "Hacer la compra"),
                Todo(1, "Pasear al perro")
            )
        )
    }

    NivelTheme {
        TodoListScreen(todos)
    }

}