package com.shaza.photoschallange.model.repo

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.shaza.photoschallange.model.Photo
import com.shaza.photoschallange.model.PhotosDataSource
import com.shaza.photoschallange.network.RetrofitClient
import org.koin.core.annotation.Factory

/**
 * Created by Shaza Hassan on 30/May/2023.
 */

@Factory
class PhotoRepoImp(private val retrofitClient: RetrofitClient) : PhotoRepo {
    override fun getPhotos(): LiveData<PagingData<Photo>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 10
            ),
            pagingSourceFactory = { PhotosDataSource(retrofitClient) }
        ).liveData
    }
}