package api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    val retrofit: PunkApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.punkapi.com/v2/beers/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PunkApiService::class.java)
    }
}