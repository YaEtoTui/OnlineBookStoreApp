package sazhin.onlinebookstoreapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import sazhin.onlinebookstoreapp.data.model.entity.BookDbEntity

@Dao
interface BooksDao {
    @Query("SELECT * FROM BookDbEntity WHERE is_order = false")
    suspend fun getAllBookInCart(): List<BookDbEntity>

    @Query("SELECT * FROM BookDbEntity WHERE is_order = true")
    suspend fun getAllOrders(): List<BookDbEntity>

    @Insert
    suspend fun insert(boobDbEntity: BookDbEntity)

    @Update(entity = BookDbEntity::class)
    suspend fun update(boobDbEntity: BookDbEntity)

    @Delete(entity = BookDbEntity::class)
    suspend fun delete(boobDbEntity: BookDbEntity)
}