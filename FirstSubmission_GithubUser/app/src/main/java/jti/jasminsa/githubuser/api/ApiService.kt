package jti.jasminsa.githubuser.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getUser(
        @Query("q") Login : String
    ): Call<GithubResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getDetailUser(
        @Path("username") username: String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowers(
        @Path("username") username: String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFllowong(
        @Path("username") username: String
    ): Call<DetailUserResponse>
}