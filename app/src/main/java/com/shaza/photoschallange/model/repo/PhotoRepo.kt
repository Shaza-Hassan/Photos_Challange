package com.shaza.photoschallange.model.repo

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.shaza.photoschallange.model.Photo

/**
 * Created by Shaza Hassan on 30/May/2023.
 */
interface PhotoRepo {

    fun getPhotos() : LiveData<PagingData<Photo>>
}