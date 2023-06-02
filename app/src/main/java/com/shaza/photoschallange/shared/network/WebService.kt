package com.shaza.photoschallange.shared.network

import com.shaza.photoschallange.photolist.model.Photos
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Shaza Hassan on 30/May/2023.
 */
interface WebService {

    @GET("?method=flickr.photos.search&format=json&nojsoncallback=50&safe_search=1&text=Color&api_key=d17378e37e555ebef55ab86c4180e8dc")
    suspend fun getImages(@Query("page") page: Int, @Query("per_page") limit: Int): Response<Photos>

}