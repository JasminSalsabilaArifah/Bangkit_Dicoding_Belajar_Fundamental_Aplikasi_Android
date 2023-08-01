package jti.jasminsa.githubuser.repository

import android.app.Application
import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import jti.jasminsa.githubuser.data.local.entity.FavoriteEntity
import jti.jasminsa.githubuser.data.local.room.FavoriteDao
import jti.jasminsa.githubuser.data.local.room.FavoriteDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteRepoitory(application: Application) {
    private val mFavoriteDao: FavoriteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = FavoriteDatabase.getDatabase(application)
        mFavoriteDao = db.favoriteDao()
    }

    fun getAllFavorite(): LiveData<List<FavoriteEntity>> = mFavoriteDao.getAllFavorite()

    fun insert(favorite: FavoriteEntity) {
        executorService.execute { mFavoriteDao.insert(favorite) }
    }
    fun delete(favorite: FavoriteEntity) {
        executorService.execute { mFavoriteDao.delete(favorite) }
    }

    fun isFavorited(login: String) = mFavoriteDao.isFavorited(login)

}