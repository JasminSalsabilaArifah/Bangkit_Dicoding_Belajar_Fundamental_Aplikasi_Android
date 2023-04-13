package jti.jasminsa.githubuser

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @JvmSuppressWildcards
    @GET("search/users")
    fun getUser(
        @Query("q") Login : String
    ): Call<GithubResponse>
}