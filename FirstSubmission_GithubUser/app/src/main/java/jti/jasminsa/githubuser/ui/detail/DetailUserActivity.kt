package jti.jasminsa.githubuser.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import jti.jasminsa.githubuser.R
import jti.jasminsa.githubuser.data.local.entity.FavoriteEntity
import jti.jasminsa.githubuser.databinding.ActivityDetailUserBinding

@Suppress("INFERRED_TYPE_VARIABLE_INTO_POSSIBLE_EMPTY_INTERSECTION")
class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var mFavoriteEntity: FavoriteEntity
    private val detailViewModel by viewModels<DetailViewModel>(){
        ViewModelFactory.getInstance(application)
    }
    var stts = ""

    companion object {
        const val TAG = "DetailUserActivity"
        const val USERNAME = "username"
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_2,
            R.string.tab_text_1
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val gus = intent.getStringExtra(USERNAME)

        detailViewModel.detailUser(gus.toString())
        detailViewModel.dataDetailUser().observe(this, {brt ->
            brt?.let {
                with(binding) {
                    tvName.text = brt.name ?: ""
                    tvUserName.text = brt.login ?: ""
                    tvFollowers.text = "${brt.followers} Followers"
                    tvFollowing.text = "${brt.following} Following"
                    Glide.with(this@DetailUserActivity)
                        .load(brt.avatarUrl)
                        .into(ivDetailProfile)
                    mFavoriteEntity = FavoriteEntity(brt.login.toString(), brt.avatarUrl)
                    detailViewModel.isFavorited(mFavoriteEntity.login).observe(this@DetailUserActivity){
                        setButtonFavorite(it)
                        dataFavorite(it)
                    }
                }
        }})
        detailViewModel.isLoading.observe(this, {
            showLoading(it)
        })

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        sectionsPagerAdapter.username = gus.toString()
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun setButtonFavorite(data: Boolean) {
        binding.btnFavorite.apply {
            if (data) {
                setImageDrawable(ContextCompat.getDrawable(
                    this@DetailUserActivity,
                    R.drawable.ic_favorited_24
                ))

            } else {
                setImageDrawable(
                    ContextCompat.getDrawable(
                        this@DetailUserActivity,
                        R.drawable.ic_favorite_border_24
            ))
            }
        }
    }

    private fun dataFavorite(data: Boolean){
        binding.btnFavorite.apply {
            setOnClickListener {
                if (data) {
                    detailViewModel.delete(mFavoriteEntity)
                } else {
                    detailViewModel.insert(mFavoriteEntity)
                }
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}