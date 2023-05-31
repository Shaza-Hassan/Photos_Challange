package com.shaza.photoschallange.model

import com.shaza.photoschallange.network.RetrofitClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.annotation.Factory

/**
 * Created by Shaza Hassan on 30/May/2023.
 */

@Factory
class PhotoRepoImp (private val retrofitClient: RetrofitClient): PhotoRepo {
    override suspend fun getPhotos(page: Int, limit: Int): Flow<Photos> {
        return flow {
            val response = retrofitClient.webService.getImages(page, limit)
            if (response.isSuccessful){
                response.body()?.let {
                    emit(it)
                    return@flow
                } ?: run {
                    error("Empty Body Response")
                }

            }else{
                val error = response.errorBody()
                error("code:${response.code()} messsge: ${error}")
            }
        }
    }
}