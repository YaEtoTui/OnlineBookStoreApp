package sazhin.onlinebookstoreapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import sazhin.onlinebookstoreapp.data.dao.BooksDao
import sazhin.onlinebookstoreapp.data.model.entity.BookDbEntity

@Database(entities = [BookDbEntity::class], version = 1)
abstract class BookDatabase : RoomDatabase() {
    abstract fun booksDao(): BooksDao
}