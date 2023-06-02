package com.shaza.photoschallange.photolist.model.repo

import androidx.paging.PagingData
import com.shaza.photoschallange.photolist.model.Photo
import kotlinx.coroutines.flow.Flow

/**
 * Created by Shaza Hassan on 30/May/2023.
 */
interface PhotoRepo {

    fun getPhotos(): Flow<PagingData<Photo>>
}