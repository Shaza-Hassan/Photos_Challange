package com.shaza.photoschallange.shared.koin

import androidx.room.Room
import com.shaza.photoschallange.shared.room.PhotosDB
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.dsl.module

/**
 * Created by Shaza Hassan on 30/May/2023.
 */

@Module
@ComponentScan("com.shaza.photoschallange")
class AppModule

val module = module {
    single {
        Room.databaseBuilder(get(), PhotosDB::class.java, "PHOTOS_DB.db").build()
    }

    single {
        val db = get<PhotosDB>()
        db.getPhotoDao()
        db.getRemoteKeysDao()
    }
}