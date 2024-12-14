package sazhin.onlinebookstoreapp.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class BookDbEntity(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "page") val page: Int?,
    @ColumnInfo(name = "path_photo") val pathPhoto: String?,
    @ColumnInfo(name = "price") val price: Int?,
    @ColumnInfo(name = "author_name") val authorName: String?

)