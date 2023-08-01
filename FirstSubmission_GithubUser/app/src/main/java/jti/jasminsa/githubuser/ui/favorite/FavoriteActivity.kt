package jti.jasminsa.githubuser.ui.favorite

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import jti.jasminsa.githubuser.data.remote.response.ItemsItem
import jti.jasminsa.githubuser.databinding.ActivityFavoriteBinding
import jti.jasminsa.githubuser.ui.detail.ViewModelFactory


class FavoriteActivity: AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private val favoriteViewModel by viewModels<FavoriteViewModel> {
        ViewModelFactory.getInstance(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        favoriteViewModel.getAllFvorite().observe(this){users ->
            val items = arrayListOf<ItemsItem>()
            users.map {
                val item = ItemsItem(login = it.login, avatarUrl = it.avatarUrl)
                items.add(item)
            }
            binding.rvUserFav.layoutManager = LinearLayoutManager(this@FavoriteActivity)
            binding.rvUserFav.adapter = FavoriteAdapter(items)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}