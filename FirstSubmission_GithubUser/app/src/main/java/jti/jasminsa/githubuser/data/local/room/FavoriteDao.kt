package jti.jasminsa.githubuser.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import jti.jasminsa.githubuser.data.local.entity.FavoriteEntity

@Dao
interface  FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(favorite:FavoriteEntity)

    @Delete
    fun delete(favorite:FavoriteEntity)

    @Query("SELECT * from tbFavorite")
    fun getAllFavorite(): LiveData<List<FavoriteEntity>>

    @Query("SELECT EXISTS(SELECT * FROM tbFavorite WHERE login = :login)")
    fun isFavorited(login: String): LiveData<Boolean>
}