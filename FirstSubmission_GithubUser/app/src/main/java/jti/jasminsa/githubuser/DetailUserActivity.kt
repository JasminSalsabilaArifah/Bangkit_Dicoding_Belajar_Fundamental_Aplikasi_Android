package jti.jasminsa.githubuser

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import jti.jasminsa.githubuser.api.ApiConfig
import jti.jasminsa.githubuser.api.DetailUserResponse
import jti.jasminsa.githubuser.databinding.ActivityDetailUserBinding
import jti.jasminsa.githubuser.databinding.ActivityMainBinding

@Suppress("INFERRED_TYPE_VARIABLE_INTO_POSSIBLE_EMPTY_INTERSECTION")
class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding

    companion object {
        const val TAG = "DetailUserActivity"
        const val USERNAME = "username"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_detail_user)
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val gus = if (Build.VERSION.SDK_INT >= 33) {
             intent.getParcelableExtra(USERNAME, TAG::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(USERNAME)
        }

        val detailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailViewModel::class.java)
        detailViewModel.detailUser(gus.toString())
        detailViewModel.dataDetailUser().observe(this, {brt ->
            Log.e(TAG, "set setail user $brt}")
            if (brt != null) {
                binding.tvUserName.text = brt.login
                binding.tvName.text = brt.name ?: ""
                Glide.with(this)
                    .load(brt.avatarUrl)
                    .into(binding.ivDetailProfile)
            }
        })
        detailViewModel.isLoading.observe(this, {
            showLoading(it)
        })
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

