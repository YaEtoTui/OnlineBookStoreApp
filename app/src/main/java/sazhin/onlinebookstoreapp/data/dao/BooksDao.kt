package sazhin.onlinebookstoreapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import sazhin.onlinebookstoreapp.data.model.entity.BookDbEntity

@Dao
interface BooksDao {
    @Query("SELECT * FROM BookDbEntity")
    suspend fun getAll(): List<BookDbEntity>

    @Insert
    suspend fun insert(filmDbEntity: BookDbEntity)
}