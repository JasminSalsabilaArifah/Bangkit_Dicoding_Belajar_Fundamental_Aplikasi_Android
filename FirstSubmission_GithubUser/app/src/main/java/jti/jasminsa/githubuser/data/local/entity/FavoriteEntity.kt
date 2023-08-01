package jti.jasminsa.githubuser.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbFavorite")
class FavoriteEntity(
    @field:ColumnInfo(name = "login")
    @field:PrimaryKey
    val login: String = "",

    @field:ColumnInfo(name = "avatarUrl")
    val avatarUrl: String? = null,

    @field:ColumnInfo(name = "favorite")
    var isFavorite: Boolean = false
)