package jti.jasminsa.githubuser.ui.favorite

import android.app.Application
import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import jti.jasminsa.githubuser.data.local.entity.FavoriteEntity
import jti.jasminsa.githubuser.repository.FavoriteRepoitory

class FavoriteViewModel (application: Application) : ViewModel() {
    private val mFavoriteRepoitory: FavoriteRepoitory = FavoriteRepoitory(application)

    fun getAllFvorite(): LiveData<List<FavoriteEntity>> = mFavoriteRepoitory.getAllFavorite()
}