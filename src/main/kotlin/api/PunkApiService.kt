package api

import model.BeerData
import retrofit2.Call
import retrofit2.http.GET

interface PunkApiService {

//    @GET("random")
//    fun loadImages(): Call<List<BeerData>>


    @GET("random")
    suspend fun loadImages(): List<BeerData>
}

