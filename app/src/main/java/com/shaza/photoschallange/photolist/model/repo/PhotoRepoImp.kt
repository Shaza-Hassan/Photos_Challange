package com.shaza.photoschallange.photolist.model.repo

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.shaza.photoschallange.shared.network.RetrofitClient
import com.shaza.photoschallange.photolist.model.Photo
import com.shaza.photoschallange.photolist.model.PhotoRemoteMediator
import com.shaza.photoschallange.shared.room.PhotosDB
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

/**
 * Created by Shaza Hassan on 30/May/2023.
 */

@Factory
class PhotoRepoImp(private val retrofitClient: RetrofitClient, private val photosDB: PhotosDB) :
    PhotoRepo {
    @OptIn(ExperimentalPagingApi::class)
    override fun getPhotos(): Flow<PagingData<Photo>> {
        return Pager(
            config = PagingConfig(
                pageSize = 12,
            ),
            pagingSourceFactory = { photosDB.getPhotoDao().getPhotos() },
            remoteMediator = PhotoRemoteMediator(retrofitClient, photosDB)
        ).flow
    }
}