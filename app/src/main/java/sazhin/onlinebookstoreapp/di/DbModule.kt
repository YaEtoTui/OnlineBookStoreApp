package sazhin.onlinebookstoreapp.di

import android.content.Context
import androidx.room.Room
import org.koin.dsl.module
import sazhin.onlinebookstoreapp.data.db.BookDatabase
import java.util.Objects.isNull

val dbModule = module {
    single { DataBaseBuilder.getInstance(get()) }
}

object DataBaseBuilder {

    private var INSTANCE: BookDatabase? = null

    fun getInstance(context: Context): BookDatabase {
        if (isNull(INSTANCE)) {
            synchronized(BookDatabase::class) {
                INSTANCE = buildRoomDB(context)
            }
        }

        return INSTANCE!!
    }

    private fun buildRoomDB(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            BookDatabase::class.java,
            "books-in-cart"
        ).build()
}