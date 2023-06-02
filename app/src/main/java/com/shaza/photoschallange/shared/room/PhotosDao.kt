package com.shaza.photoschallange.shared.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shaza.photoschallange.photolist.model.Photo

/**
 * Created by Shaza Hassan on 02/Jun/2023.
 */
@Dao
interface PhotosDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<Photo>)

    @Query("Select * From photo")
    fun getPhotos(): PagingSource<Int, Photo>

    @Query("Delete From photo")
    suspend fun clearAllMovies()

}