package com.shaza.photoschallange.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shaza.photoschallange.photolist.model.Photo

/**
 * Created by Shaza Hassan on 02/Jun/2023.
 */

@Database(
    entities = [Photo::class, RemoteKeys::class],
    version = 1,
    exportSchema = true
)


abstract class PhotosDB : RoomDatabase() {
    abstract fun getPhotoDao(): PhotosDao
    abstract fun getRemoteKeysDao(): RemoteKeysDao
}