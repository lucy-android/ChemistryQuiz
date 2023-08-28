package by.budanitskaya.l.chemistryquiz.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST

private const val BASE_URL =
    "https://localhost:3000/"


private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface ApiService {
    @POST
    suspend fun login()
}

object GifApi {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}