package jti.jasminsa.githubuser

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import jti.jasminsa.githubuser.api.ApiConfig
import jti.jasminsa.githubuser.api.DetailUserResponse
import jti.jasminsa.githubuser.api.ItemsItem
import retrofit2.Response
import retrofit2.Callback
import retrofit2.Call
import java.util.*

class DetailViewModel : ViewModel() {

    private val _detailUser = MutableLiveData<DetailUserResponse?>()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object{
        private const val TAG = "DetailViewModel"
        private const val EXUSERNAME = "exusername"
    }

//    init {
//        detailUser()
//    }

    fun detailUser(username : String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetailUser(username)
        client.enqueue(object : Callback<DetailUserResponse> {
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _detailUser.postValue(responseBody)
                    }
                } else {
                    Log.e(DetailUserActivity.TAG, "onFailure2: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(DetailUserActivity.TAG, "onFailure1: ${t.message}")
            }
        })
    }

    fun dataDetailUser (): MutableLiveData<DetailUserResponse?> {
        return _detailUser
    }
}