package com.shaza.photoschallange.model

import kotlinx.coroutines.flow.Flow

/**
 * Created by Shaza Hassan on 30/May/2023.
 */
interface PhotoRepo {

    suspend fun getPhotos(page:Int,limit: Int) : Flow<Photos>
}