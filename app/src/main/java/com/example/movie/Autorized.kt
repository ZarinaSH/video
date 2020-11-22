package com.example.movie

import retrofit2.Call
import retrofit2.http.*

 const val API_KEY = "cf3f93c770820648b653a9e763863cb3"

interface AuthorizedApi {
    @GET("authentication/token/new")
    fun createToken(@Query("api_key") api_key: String = API_KEY): Call<ResponseCreateToken>

    @POST("authentication/token/validate_with_login?")
    fun postLiP(@Query("api_key") api_key: String = API_KEY, @Body user: User):Call<ResponseCreateToken>

    @POST("authentication/token/validate_with_login?")
    fun sesionID(@Query("api_key") api_key: String = API_KEY, @Body token:String):Call<LastPostData>

}
//interface WeatherAPI {
//    @GET("/api/location/search/")
//    fun searchSity(@Query("query") query: String): Call<List<CityItem>>
//}
//
//
//interface PogodaAPI {
//    @GET("/api/location/{cityid}")
//    fun pogoda(@Path("cityid") cityid: Int): Call<WeatherResponse>
//
//}