package com.example.pmdm.todolist.data.local

import androidx.room.Dao
import androidx.room.Query
import com.example.pmdm.todolist.data.local.entities.Todo
import kotlinx.coroutines.flow.Flow

@Dao
internal interface TodoDao {
    @Query("SELECT * FROM todos")
    fun getAll(): Flow<List<Todo>>

    @Query("SELECT * FROM todos WHERE id = :id")
    suspend fun getById(id: Int): Todo


}