package jti.jasminsa.githubuser

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import jti.jasminsa.githubuser.databinding.ActivityDetailUserBinding

@Suppress("INFERRED_TYPE_VARIABLE_INTO_POSSIBLE_EMPTY_INTERSECTION")
class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding

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

        val gus = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(USERNAME, TAG::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(USERNAME)
        }

        val mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)
        mainViewModel.detailUser(gus.toString())
        mainViewModel.dataDetailUser().observe(this, {brt ->
            brt?.let {
                with(binding) {
                    tvName.text = brt.name ?: ""
                    tvUserName.text = brt.login ?: ""
                    tvFollowers.text = "${brt.followers} Followers"
                    tvFollowing.text = "${brt.following} Following"
                    Glide.with(this@DetailUserActivity)
                        .load(brt.avatarUrl)
                        .into(ivDetailProfile)
                }
        }})
        mainViewModel.isLoading.observe(this, {
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

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}