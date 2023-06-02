package com.shaza.photoschallange.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Shaza Hassan on 02/Jun/2023.
 */
@Entity(tableName = "remote_key")
data class RemoteKeys(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo
    val id: String,
    val prevKey: Int?,
    val currentPage: Int,
    val nextKey: Int?,
    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis()
)
